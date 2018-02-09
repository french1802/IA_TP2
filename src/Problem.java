import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.exit;

class Problem {
    private String cityStart;
    private String cityEnd;
    private ArrayList<String> cities = new ArrayList<>();
    private ArrayList<Action> actions = new ArrayList<>();

    Problem() {

        try {

            File xmlFileData = new File("xmlFiles\\data.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFileData);

            // Load the list of cities
            NodeList citylist = document.getElementsByTagName("City");
            for (int i = 0; i < citylist.getLength(); i++) {
                org.w3c.dom.Node node = citylist.item(i);

                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    cities.add(element.getElementsByTagName("Name").item(0).getTextContent());

                }
            }

            NodeList list = document.getElementsByTagName("Path");
            String startCity;
            String endCity;
            int cost = -1;

            for (int i = 0; i < list.getLength(); i++) {
                org.w3c.dom.Node node = list.item(i);

                if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    startCity = element.getElementsByTagName("StartCity").item(0).getTextContent();
                    endCity = element.getElementsByTagName("EndCity").item(0).getTextContent();

                    System.out.print(startCity + " -> " + endCity + " : ");
                    try {
                        System.out.println(element.getElementsByTagName("Cost").item(0).getTextContent());
                        cost = Integer.parseInt(element.getElementsByTagName("Cost").item(0).getTextContent());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        exit(-1);
                    }

                    addAction(startCity, endCity, cost);
                }
            }

            File xmlFileProblem = new File("xmlFiles\\travel.xml");
            document = documentBuilder.parse(xmlFileProblem);

            NodeList travel = document.getElementsByTagName("Travel");
            org.w3c.dom.Node node = travel.item(0);

            if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element element = (Element) node;

                this.cityStart = element.getElementsByTagName("StartCity").item(0).getTextContent();
                this.cityEnd = element.getElementsByTagName("EndCity").item(0).getTextContent();
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

        private void addAction(String startState, String endState, int cost) {
        actions.add(new Action(startState, endState, cost));
    }

    ArrayList<Action> successors(String state) {
        ArrayList<Action> succession = new ArrayList<>();
        for (Action action : actions) {
            if (action.getStartState().equals(state)) {
                succession.add(action);
            }
        }
        return succession;
    }

    String getCityStart() {
        return this.cityStart;
    }

    int getCost(String startCity, String endCity) {
        int i = 0;
        int size = actions.size();
        while (i < size) {
            Action a = actions.get(i);
            if (a.getStartState().equals(startCity) && a.getEndState().equals(endCity)) {
                return a.getCost();
            }
            i++;
        }
        return 0;
    }

    boolean goalTest(Node node) {
        return (node.getState().equals(this.cityEnd));
    }
}
