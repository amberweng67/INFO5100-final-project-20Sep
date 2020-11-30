package dto;

import dao.Address;
import dao.Dealer;
import dao.GenericModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataPersistence {

    private static final String DATA_PATH = "./INFO5100-final-project-20Sep/data/";
    // Reads dealers file in data directory and returns a map of dealers with
    // the dealer's ids as its keys and its corresponding Dealer object as the value
    public Map<String, Dealer> readDealerFile() throws IOException, FileNotFoundException{
        Map<String, Dealer> result = new HashMap<>();
        String dealerFilePath = DATA_PATH + "dealers.csv";
        File csv = new File(dealerFilePath);
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(csv));

        String line = "";
        line = br.readLine();
        while (line != null) {
            String[] fields = line.split(",");
            for (int i = 0; i < fields.length; i++) {
                System.out.print(i + " " + fields[i] + " ");
            }
            System.out.println();
            String dealerId = fields[0];
            String dealerName = fields[1];
            String address1 = fields[2];
            String address2 = fields[3];
            String city = fields[4];
            String state = fields[5];
            String dealerZipCode = fields[6];
            Address dealerAddress = new Address(address1, address2, city, state, dealerZipCode);
            Dealer d = new Dealer(dealerId, dealerName, dealerAddress);
            result.put(d.getDealerId(), d);
            line = br.readLine();
        }
        return result;

    }

    public void saveDealersToFile(Map<String, Dealer> dealerMap) throws IOException{
        String dealerFilePath = DATA_PATH + "dealers.csv";
        File csv = new File(dealerFilePath);
        BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));

        for (String dealerId: dealerMap.keySet()) {
            Dealer d = dealerMap.get(dealerId);
            bw.write(d.toCSVLine());
            bw.newLine();
        }
        bw.close();

    }
}
