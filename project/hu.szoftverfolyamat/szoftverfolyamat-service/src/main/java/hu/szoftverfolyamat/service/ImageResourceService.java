package hu.szoftverfolyamat.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import hu.szoftverfolyamat.entity.ImageResourceEntity;
import hu.szoftverfolyamat.repository.ImageResourceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageResourceService {
	
	private final static int IMAGE_MAX_SIZE_IN_PIXEL = 400;

	@Autowired
	private ImageResourceRepository imageResourceRepository;

	public byte[] getImageSource(Long id) {
		ImageResourceEntity entity;

		entity = this.imageResourceRepository.findOne(id);
		if (entity == null) {
			return null;
		}
		return entity.getImage();
	}

	public Long saveImage(byte[] imageSource, String imageFormat) throws IOException {
		ImageResourceEntity entity;

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			entity = new ImageResourceEntity();
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageSource));
			image = this.compressImage(image);
			ImageIO.write(image, imageFormat, baos);
			baos.flush();
			entity.setImage(baos.toByteArray());
			return this.imageResourceRepository.saveAndFlush(entity).getId();
		}
		
	}
	
	
	private BufferedImage compressImage(BufferedImage image) {
		
		boolean heightIsDominant = false;
		
		if(image.getWidth() <= IMAGE_MAX_SIZE_IN_PIXEL) {
			if(image.getHeight() <= IMAGE_MAX_SIZE_IN_PIXEL) {
				// nincs szükség tömöríteni
				return image;
			} else {
				// a szélesség megfelelő volt, de a magasság túl nagy
				heightIsDominant = true;
			}
		}
		
		// ennyivel kell szorozni a kép szélességet és magasságot (0-1 közötti szám)
		double multiplier = ((double) IMAGE_MAX_SIZE_IN_PIXEL) / (heightIsDominant ? image.getHeight() : image.getWidth());
		int width = (int) (image.getWidth() * multiplier);
		int height = (int) (image.getHeight() * multiplier);
		int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
		
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D graphics = resizedImage.createGraphics();
		graphics.drawImage(image, 0, 0, width, height, null);
		graphics.dispose();
		return resizedImage;
		
	}
	
}
