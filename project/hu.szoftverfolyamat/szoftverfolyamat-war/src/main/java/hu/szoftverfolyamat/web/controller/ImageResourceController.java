package hu.szoftverfolyamat.web.controller;

import hu.szoftverfolyamat.service.ImageResourceService;
import hu.szoftverfolyamat.service.UserProfileDataService;
import hu.szoftverfolyamat.web.helper.URI;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.security.Principal;

@Controller
public class ImageResourceController extends BaseController {

	@Autowired
	private ImageResourceService imageResourceService;

	@Autowired
	private UserProfileDataService userProfileDataService;

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
	public @ResponseBody byte[] getImage(@PathVariable("id") Long id) {
		byte[] result;

		result = imageResourceService.getImageSource(id);
		if (result == null) {
			return this.devaultAvatarInBytes;
		}
		return result;
	}

	@RequestMapping(value = URI.UPLOAD_IMAGE, method = RequestMethod.POST)
	public @ResponseBody ModelAndView uploadImage(final Principal principal,
			@RequestParam("file") MultipartFile file) {
		ModelAndView modelAndView;
		Long avatarId;

		if (!file.isEmpty()) {
			byte[] bytes;
			try {
				bytes = file.getBytes();
				String extension = this.getExtension(file.getOriginalFilename());
				extension = extension != null ? extension.toLowerCase() : null;
				if(!"png".equals(extension) && !"jpg".equals(extension) && !"jpeg".equals(extension) && !"gif".equals(extension)) {
					// TODO hibakezelés (nem támogatott fájlformátum)
					return new ModelAndView("redirect:/");
				}
				avatarId = this.imageResourceService.saveImage(bytes, extension);
				userProfileDataService.updateAvatarId(
						this.getCurrentUser(principal), avatarId);
			} catch (IOException e) {
				// TODO logolás
				e.printStackTrace();
			}

		}
		return new ModelAndView("redirect:/");
	}
	
	private String getExtension(String filenameFromBrowser) {
		if(filenameFromBrowser == null) return null;
		String[] filenameParts = filenameFromBrowser.split("\\.");
		return filenameParts[filenameParts.length - 1];
	}
}
