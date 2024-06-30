package na.kuroneko.arenagame2.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlayerData {
    // Gson 객체를 생성해서 Json 형식의 데이터를 처리
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    // 플레이어 데이터를 저장할 디렉토리의 경로를 설정
    private static final Path DATA_FOLDER = Paths.get("./plugins/ArenaGame2/playerdata");

    // 각 내용을 저장하는 필드
    private String playerName;
    private int highestRound;
    private double averageRound;
    private int playTime;

    // 생성자에선 플레이어의 이름을 설정하고, 나머지는 필드 초기화
    public PlayerData(String playerName) {
        this.playerName = playerName;
        this.highestRound = 0;
        this.averageRound = 0;
        this.playTime = 0;
    }

    // 각 필드에 대한 getter와 setter
    // 이 메서드를 통해 필드의 값을 가져오거나 설정할 수 있다.
    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getHighestRound() {
        return highestRound;
    }

    public void setHighestRound(int highestRound) {
        this.highestRound = highestRound;
    }

    public double getAverageRound() {
        return averageRound;
    }

    public void setAverageRound(double averageRound) {
        this.averageRound = averageRound;
    }

    // PlayerData 객체의 정보를 Json 형식으로 변환해서 파일에 저장
    public void saveToJson() {
        try {
            // playerdata가 있는지 확인하고 없다면 생성
            if (!Files.exists(DATA_FOLDER)) {
                Files.createDirectories(DATA_FOLDER);
            }
            // FileWriter 객체를 생성하여 Json 파일에 쓸 수 있도록 한다.
            try (FileWriter writer = new FileWriter(DATA_FOLDER.resolve(playerName + ".json").toFile())) {
                // PlayerData 객체를 Json 형식의 문자열로 변환하고 이 문자열을 Json 파일에 쓴다.
                GSON.toJson(this, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Json 파일에서 PlayerData 객체를 불러옴
    public static PlayerData loadFromJson(String playerName) {
        // 플레이어 이름을 사용하여 Json 파일의 경로를  결정
        Path filePath = DATA_FOLDER.resolve(playerName + ".json");
        // 파일이 존재하는지 확인
        if (Files.exists(filePath)) {
            try (FileReader reader = new FileReader(filePath.toFile())) {
                // Json 파일의 내용을 PlayerData 객체로 변환
                return GSON.fromJson(reader, PlayerData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 파일이 존재하지 않을 경우 새로운 PlayerData 객체를 생성하여 반환
        return new PlayerData(playerName);
    }
}