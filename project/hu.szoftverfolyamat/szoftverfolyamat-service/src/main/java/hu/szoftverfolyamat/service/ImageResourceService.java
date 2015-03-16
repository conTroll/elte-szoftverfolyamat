package hu.szoftverfolyamat.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import hu.szoftverfolyamat.entity.ImageResourceEntity;
import hu.szoftverfolyamat.repository.ImageResourceRepository;

import org.hibernate.cfg.IndexOrUniqueKeySecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageResourceService {

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

	public Long saveImage(byte[] imageSource) {
		ImageResourceEntity entity;

		entity = new ImageResourceEntity();
		entity.setImage(imageSource);
		return this.imageResourceRepository.saveAndFlush(entity).getId();
	}
}
