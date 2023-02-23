package com.example.IncedoHackathon.Controllers;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.IncedoHackathon.Entities.IdeaSubmission;
import com.example.IncedoHackathon.Helper.FileUploadHelper;
import com.example.IncedoHackathon.Repositories.IdeaSubmissionRepository;


@RestController
@CrossOrigin("*")
public class FileUploadController {

	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@Autowired
	private IdeaSubmissionRepository ideaSubmissionRepository;

	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("teamName") String teamName) {

		try {

			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");

			}
			// file upload code..
//		fileUploadHelper.uploadFile(file);

			boolean f = fileUploadHelper.uploadFile(file, teamName);
			if (f) {
				return ResponseEntity.ok("File is successfully uploaded");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!  Try again");

	}

	@GetMapping("/download/{teamName}")
	public ResponseEntity<Object> downloadFileFromLocal(@PathVariable("teamName") String teamName) {
		IdeaSubmission ideaSubmission = ideaSubmissionRepository.findByRegistrationTeamName(teamName);
		String fileDirectory = "src/main/resources/static/Final submission/" + teamName+ "/" + ideaSubmission.getFinalSubmission();
		
		Path path = Paths.get(fileDirectory);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
