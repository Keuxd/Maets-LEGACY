package maets.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class ConfigFile {
	
	public enum LocalConfigs {
		GAMES_STATUS
	}
	
	public enum OnlineConfigs {
		GAMES_IN_LIBRARY, PLAY_TIME, LAST_SESSION
	}
	
	private final String CONFIG_VALUE_SEPARATOR = "<";
	private final String CONFIGS_SEPARATOR = ">";
	private final String VALUES_SEPARATOR = "~";

	private File configFile;
	private FileChannel configFileChannel;
	
	private String[] configurationsInFile;
	
	public ConfigFile(Class<? extends Enum<?>> enumClass) {
		this.configurationsInFile = Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
	
	public ConfigFile(List<String> configurationsInFile) {
		this.configurationsInFile = configurationsInFile.toArray(new String[0]);
	}

	public ConfigFile(String[] configurationsInFile) {
		this.configurationsInFile = configurationsInFile;
	}

	public void initializeFile(String folderPath, String fileName) throws IOException {
		if(configFile != null) throw new IOException("File already initialized");
		
		configFile = new File(folderPath + "\\" + fileName + ".ini");
		configFile.getParentFile().mkdir();
		
		// If true then the file didn't exist previously (Sucessfuly created)
		if(configFile.createNewFile()) {
			writeDefaultInfoInConfigFile();
			createFileChannel();
		} else {
			createFileChannel();
			String[] content = getContentByConfig();
			configFile.delete();
			configFile.createNewFile();
			writeDefaultInfoInConfigFile();

			for(int i = 0; i < content.length; i++) {
				String[] configAndValue = content[i].split(CONFIG_VALUE_SEPARATOR);
				
				if(configAndValue.length > 1)
					addValueToConfig(configAndValue[0], configAndValue[1]);
			}
		}
		
		configFileChannel.lock();
	}
	
	private void writeDefaultInfoInConfigFile() throws IOException {
		FileWriter fWriter = new FileWriter(configFile.getAbsolutePath());
		StringBuilder sb = new StringBuilder();
		
		for(String config : configurationsInFile) {
			sb.append(config);
			sb.append(CONFIG_VALUE_SEPARATOR + CONFIGS_SEPARATOR);
		}
		
		fWriter.write(sb.toString());
		fWriter.close();
	}
	
	private void writeDefaultInfoInConfigFileChannel() throws IOException {
		StringBuilder sb = new StringBuilder();
		
		for(String config : configurationsInFile) {
			sb.append(config);
			sb.append(CONFIG_VALUE_SEPARATOR + CONFIGS_SEPARATOR);
		}
		
		byte[] byteArray = sb.toString().getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(byteArray);
		configFileChannel.write(buffer, 0);
	}
	
	public void addValueToConfig(Enum<?> e, String value) throws IOException {
		addValueToConfig(e.name(), value);
	}
	
	public void addValueToConfig(String configName, String value) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		String[] contentByConfig = getContentByConfig();
		for(String content : contentByConfig) {
			String[] splittedContent = content.split(CONFIG_VALUE_SEPARATOR);
			
			if(splittedContent[0].equals(configName)) {
				sb.append(splittedContent[0]);
				sb.append(CONFIG_VALUE_SEPARATOR);
				
				if(splittedContent.length > 1) {
					String[] valuesInFile = splittedContent[1].split(VALUES_SEPARATOR);
					for(String valueInFile : valuesInFile) {
						sb.append(valueInFile);
						sb.append(VALUES_SEPARATOR);
					}
				}				
				sb.append(value);
				sb.append(CONFIGS_SEPARATOR);
			} else {
				sb.append(content);
				sb.append(CONFIGS_SEPARATOR);
			}
		}
		

		byte[] byteArray = sb.toString().getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(byteArray);
		configFileChannel.write(buffer, 0);
	}
	
	public String[] getValuesFromConfig(Enum<?> e) throws IOException {
		return getValuesFromConfig(e.name());
	}
	
	public String[] getValuesFromConfig(String config) throws IOException {
		String[] contentSplit = getContentByConfig();
		
		for(String content : contentSplit) {
			String[] splittedContent = content.split(CONFIG_VALUE_SEPARATOR);
			
			if(splittedContent.length == 1)
				continue;
			
			if(splittedContent[0].equals(config))
				return splittedContent[1].split(VALUES_SEPARATOR);
		}
		
		return null;
	}
	
	public boolean isValueInConfig(Enum<?> config, String value) throws IOException {
		return isValueInConfig(config.name(), value);
	}
	
	public boolean isValueInConfig(String config, String value) throws IOException {
		String[] values = getValuesFromConfig(config);
		
		if(values == null)
			return false;
		
		for(String currentValue : values)
			if(currentValue.equals(value))
				return true;
		
		return false;
	}
	
	private String[] getContentByConfig() throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(256);
		configFileChannel.read(buffer, 0);
		return new String(buffer.array(), StandardCharsets.UTF_8).trim().split(CONFIGS_SEPARATOR);
	}
	
	private void createFileChannel() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(configFile.getPath(), "rw");
		FileChannel fileChannel = raf.getChannel();
		this.configFileChannel = fileChannel;
	}
}