package org.mattrr78.mysqltx.client;

import org.springframework.web.client.RestTemplate;

public class SampleRestClient {
    public static final String[] NAMES = {
            "Adam", "Kate", "Eddie", "Tina", "Steve", "Leslie", "Keenan", "Amy", "Joe", "Julia",
            "Chris", "Molly", "Will", "Cecily", "Tim", "Kristen", "Michael", "Maya", "Chevy", "Gilda"
    };

    public static void main(String[] args) {
        int basePort = 6821;
        int processCount = 3;
        int postCount = NAMES.length;

        for (int i = 0; i < postCount; i++)  {
            int port = basePort + (i % processCount);
            startPersistAccountThread(port, "id-1", NAMES[i % NAMES.length]);
        }
    }

    private static void startPersistAccountThread(int port, String sequencerId, String accountName)  {
        new Thread(() -> persistAccount(port, sequencerId, accountName)).start();
    }

    private static String persistAccount(int port, String sequencerId, String accountName)  {
        RestTemplate client = new RestTemplate();
        String url = "http://localhost:" + port + "/api/sample?sequencerId=" + sequencerId + "&accountName=" + accountName;
        return client.postForObject(url, null, String.class);
    }
}
