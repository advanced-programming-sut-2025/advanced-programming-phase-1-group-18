package io.github.group18.Network.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {

	public static Map<String, String> listFilesInFolder(String folderPath) {
		// TODO: List files in folder
		HashMap<String, String> map = new HashMap<String, String>();
		// 1. Create folder object
		File folder = new File(folderPath);

		// 2. Get list of files
		File[] listOfFiles = folder.listFiles();

		// 3. Calculate MD5 hash for each file
        assert listOfFiles != null;
        for (File file : listOfFiles) {
			if (file.isFile()) {
				String fileName = file.getName();
			}
		}
		// 4. Return map of filename to hash
		return map;
//		throw new UnsupportedOperationException("List files in folder not implemented yet");
	}

	public static String getSortedFileList(Map<String, String> files) {
		// TODO: Get sorted file list
		// 1. Check if files map is empty
		if (files.isEmpty()){
			return "Repository is empty.";
		}
		// 2. Sort file names
		List<String> fileNames = new ArrayList<>(files.keySet());
		fileNames.sort(String::compareTo);
		StringBuilder sb = new StringBuilder();
		for (String fileName : fileNames) {
			sb.append(fileName).append(" ").append(files.get(fileName)).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();

		// 3. Create formatted string with names and hashes
//		throw new UnsupportedOperationException("Get sorted file list not implemented yet");
	}
}
