package com.example.IncedoHackathon.Controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import com.example.IncedoHackathon.Entities.IdeaSubmission;
import com.example.IncedoHackathon.Repositories.IdeaSubmissionRepository;
import com.example.IncedoHackathon.Entities.IdeaSubmission;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class JudgeController {
	
	@Autowired
	private IdeaSubmissionRepository ideaSubmissionRepository;
	
	
	public FTPClient setConnection() {
		FTPClient ftpClient = new FTPClient();
		
		try {
			ftpClient.connect("ftp://10.0.14.186"); //FTP server's hostname or IP address
			ftpClient.login("user", "Incedo@1234"); 
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				ftpClient.disconnect();
				throw new RuntimeException("Could not connect to FTP server");
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		InputStream inputStream = ftpClient.retrieveFileStream("ftp://10.0.14.186/");
//		File convertedFile = convertPptToPdf(inputStream); // Use Apache POI to convert PPT to PDF
//		ftpClient.disconnect();
		return ftpClient;
	}
	
	@GetMapping("/fetchIdeas")
	public List<IdeaSubmission> getData()
	{
		List<IdeaSubmission> rawList = ideaSubmissionRepository.findAll();
		List<IdeaSubmission> approvedList = null ;
		rawList.forEach((approvedIdea)-> { if(approvedIdea.getStatus()=="approved") {
			 approvedList.add(approvedIdea);
		}});
		return approvedList;
	}
	
	@GetMapping("/video")
	public @ResponseBody byte[] getVideo() throws IOException {
	    // Retrieve video file from FTP server and convert to byte array
		FTPClient ftpClient = setConnection();
		String remoteFilePath = "ftp://10.0.14.186/demovideo.mp4"; //path of video file on server
		OutputStream outputStream = new ByteArrayOutputStream();
		boolean success = ftpClient.retrieveFile(remoteFilePath, outputStream);
		byte[] videoData = ((ByteArrayOutputStream) outputStream).toByteArray();
		ftpClient.disconnect();
	    return videoData;
	}

//	@PostMapping("/submit-ratings")
//	public void submitForm(@RequestBody IdeaSubmission i) { 
//		IdeaSubmissionRepository.save(i);    
//	}    
}

