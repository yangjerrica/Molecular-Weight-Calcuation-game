package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads moleculeList from JSON data stored in file
//cited from the JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads molecule list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MoleculeList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMoleculeList(jsonObject);
    }

    // EFFECTS: reads Animals from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Animals> readAnimal() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAnimals(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses molecule list from JSON object and returns it
    private MoleculeList parseMoleculeList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MoleculeList ml = new MoleculeList(name);
        addMolecules(ml, jsonObject);
        return ml;
    }

    // MODIFIES: ml
    // EFFECTS: parses molecules entered from JSON object and adds them to molecule list
    private void addMolecules(MoleculeList ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("molecules");
        for (Object json : jsonArray) {
            JSONObject nextMolecule = (JSONObject) json;
            addMolecule(ml, nextMolecule);
        }
    }

    // MODIFIES: ml
    // EFFECTS: parses molecules entered from JSON object and adds it to molecule list
    private void addMolecule(MoleculeList ml, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Category category = Category.valueOf(jsonObject.getString("category"));
        MoleculeEntered moleculeEntered = new MoleculeEntered(name, category);
        ml.addMolecule(moleculeEntered);
    }

    private List<Animals> parseAnimals(JSONObject jsonObject) {
        JSONArray animalsJson = jsonObject.getJSONArray("Animals");
        List<Animals> animalsArray = new ArrayList<>();
        Animals cat = new Cat();
        Animals dog = new Dog();
        Animals quokka = new Quokka();
        animalsArray.add(dog);
        animalsArray.add(cat);
        animalsArray.add(quokka);

        for (Object json : animalsJson) {
            JSONObject animal = (JSONObject) json;
            int point = animal.getInt("points");
            String animalType = ((JSONObject) json).getString("type");
            if (animalType.equals("Cat")) {
                cat.setPoints(point);
            } else if (animalType.equals("Dog")) {
                dog.setPoints(point);
            } else {
                quokka.setPoints(point);
            }

        }
        return animalsArray;
    }
}
