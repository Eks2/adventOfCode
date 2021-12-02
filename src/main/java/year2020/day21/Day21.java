package year2020.day21;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day21 {

    public static final Pattern PATTERN = Pattern.compile("\\w+");

    public static long processStringStreamPart1(Stream<String> lines) {
        List<List<String>> ingredientLines = getLists(lines);
        List<Label> labels = extractLabels(ingredientLines);
        Set<String> allergens = getAllergens(labels);

        Map<String, List<Label>> allergensToLabelsMap = getAllergensToLabelsMap(labels, allergens);
        Map<String, Set<String>> allergenPossibleNames = getAllergenPossibleNames(allergensToLabelsMap);

        refineAllergensTranslation(allergenPossibleNames);

        Set<String> ingredientsThatAreAllergens = getAllergensInUnknownLanguage(allergenPossibleNames);

        return labels.stream()
                .map(Label::getIngredients)
                .flatMap(Collection::stream)
                .filter(ingredient -> !ingredientsThatAreAllergens.contains(ingredient))
                .count();
    }

    private static void refineAllergensTranslation(Map<String, Set<String>> allergenPossibleNames) {
        boolean allergensAreNotMapped = true;
        while (allergensAreNotMapped) {

            List<String> alreadyUsedUnknownNames = allergenPossibleNames.values()
                    .stream()
                    .filter(s -> s.size() == 1)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toUnmodifiableList());


            allergenPossibleNames.entrySet()
                    .stream()
                    .filter(stringSetEntry -> stringSetEntry.getValue().size() > 1)
                    .peek(stringSetEntry -> stringSetEntry.setValue(
                            stringSetEntry.getValue().stream()
                                    .filter(name -> !alreadyUsedUnknownNames.contains(name))
                                    .collect(Collectors.toSet())
                            )
                    )
                    .forEach(entrySet -> allergenPossibleNames.put(entrySet.getKey(), entrySet.getValue()));

            allergensAreNotMapped =  !allergenPossibleNames.keySet()
                    .stream()
                    .allMatch(s -> allergenPossibleNames.get(s).size() == 1);
        }
    }

    private static Set<String> getAllergensInUnknownLanguage(Map<String, Set<String>> allergenPossibleNames) {
        return allergenPossibleNames.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private static Map<String, Set<String>> getAllergenPossibleNames(Map<String, List<Label>> allergensToLabelsMap) {
        Map<String, Set<String>> allergenPossibleNames = new HashMap<>();
        for (String mapKey : allergensToLabelsMap.keySet()) {
            List<Label> labelsList = allergensToLabelsMap.get(mapKey);
            Set<String> result = new HashSet<>(labelsList.get(0).getIngredients());
            labelsList.stream()
                    .map(Label::getIngredients)
                    .forEach(result::retainAll);

            allergenPossibleNames.put(mapKey, result);
        }
        return allergenPossibleNames;
    }

    private static Map<String, List<Label>> getAllergensToLabelsMap(List<Label> labels, Set<String> allergens) {
        return allergens.stream()
                .collect(Collectors.toMap(
                        allergen -> allergen,
                        allergen -> labels.stream()
                                .filter(label -> label.getAllergens().contains(allergen))
                                .collect(Collectors.toUnmodifiableList()))
                );
    }

    private static Set<String> getAllergens(List<Label> labels) {
        return labels.stream()
                .map(Label::getAllergens)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableSet());
    }

    private static List<List<String>> getLists(Stream<String> lines) {
        return lines.map(PATTERN::matcher)
                .map(matcher -> matcher.results()
                        .map(MatchResult::group)
                        .collect(Collectors.toUnmodifiableList())
                )
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<Label> extractLabels(List<List<String>> ingredientLines) {
        List<Label> labels = new ArrayList<>();
        for (List<String> ingredientList : ingredientLines) {
            int containsIndex = ingredientList.indexOf("contains");
            Set<String> allergensInEnglish
                    = new HashSet<>(ingredientList.subList(containsIndex + 1, ingredientList.size()));
            Set<String> ingredients = new HashSet<>(ingredientList.subList(0, containsIndex));

            labels.add(new Label(allergensInEnglish, ingredients));
        }
        return labels;
    }

    public static String processStringStreamPart2(Stream<String> lines) {
        List<List<String>> ingredientLines = getLists(lines);
        List<Label> labels = extractLabels(ingredientLines);
        Set<String> allergens = getAllergens(labels);

        Map<String, List<Label>> allergensToLabelsMap = getAllergensToLabelsMap(labels, allergens);
        Map<String, Set<String>> allergenPossibleNames = getAllergenPossibleNames(allergensToLabelsMap);

        refineAllergensTranslation(allergenPossibleNames);

        return allergenPossibleNames.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.joining(","));

    }
}
