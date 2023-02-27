package com.example.IncedoHackathon.Helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.IncedoHackathon.Entities.IdeaSubmission;
import com.example.IncedoHackathon.Repositories.IdeaSubmissionRepository;



@Component
public class FileUploadHelper {
		
	@Autowired
	IdeaSubmissionRepository ideaSubmissionRepository;

	public final String UPLOAD_DIR = "C:\\Users\\user3\\Desktop\\Frontend\\src\\components\\videos";

	public boolean uploadFile(MultipartFile multipartFile, String teamName) throws IOException {
		boolean f = false;
		String uploadDirectory = UPLOAD_DIR + teamName;
		Path uploadPath = Paths.get(uploadDirectory);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try {

			InputStream is = multipartFile.getInputStream();
			byte data[] = new byte[is.available()];
			is.read(data);

			FileOutputStream fos = new FileOutputStream(
					uploadDirectory + File.separator + multipartFile.getOriginalFilename());

			fos.write(data);
			fos.flush();
			fos.close();

			f = true;
			
			String fileName = multipartFile.getOriginalFilename();
			IdeaSubmission ideaSubmission = ideaSubmissionRepository.findByRegistrationTeamName(teamName);
			ideaSubmission.setFinalSubmission(fileName);
			ideaSubmissionRepository.save(ideaSubmission);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;

	}

}
