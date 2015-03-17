package hu.szoftverfolyamat.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import hu.szoftverfolyamat.service.ImageResourceService;
import hu.szoftverfolyamat.service.UserProfileDataService;
import hu.szoftverfolyamat.web.helper.URI;
import hu.szoftverfolyamat.web.requestobject.IdRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageResourceController {
	
	

	@Autowired
	private ImageResourceService imageResourceService;

	private final byte[] devaultAvatarInBytes;

	@Autowired
	public ImageResourceController(ServletContext context) throws IOException {
		if (context == null) {
			throw new IOException("ServletContext is null");
		}
		this.devaultAvatarInBytes = IOUtils
				.toByteArray(context
						.getResourceAsStream("/WEB-INF/resources/images/defaultProfilePicture.gif"));

	}

	@RequestMapping(value = URI.GET_IMAGE, method = RequestMethod.GET)
	public @ResponseBody byte[] getDefaultImage() {
		return this.devaultAvatarInBytes;
	}

	@RequestMapping(value = URI.GET_IMAGE + "/{id}", method = RequestMethod.GET)
	public @ResponseBody byte[] getImage(
			@PathVariable("id") Long id) {
		InputStream inputStream;
	public @ResponseBody byte[] getImage(@PathVariable("id") Long id) {
		byte[] result;
		String path;
		

		result = imageResourceService.getImageSource(id);
		if(result == null) {
			inputStream = getClass().getClassLoader().getResourceAsStream(
					"WEB-INF/resources/images/defaultProfilePicture.gif");
			try {
				return IOUtils.toByteArray(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		if (result == null) {
			return this.devaultAvatarInBytes;
		}
		return result;
	}

	@RequestMapping(value = URI.UPLOAD_IMAGE, method = RequestMethod.POST)
	public @ResponseBody Long uploadImage(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
                byte[] bytes;
				try {
					bytes = file.getBytes();
					return this.imageResourceService.saveImage(bytes);
				} catch (IOException e) {
					
				}
			byte[] bytes;
			try {
				bytes = file.getBytes();
				return this.imageResourceService.saveImage(bytes);
			} catch (IOException e) {

        }
			}

		}
		return null;
	}
}
