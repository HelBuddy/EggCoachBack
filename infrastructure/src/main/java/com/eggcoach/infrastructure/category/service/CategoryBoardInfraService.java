package com.eggcoach.infrastructure.category.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eggcoach.core.domain.board.dto.CategoryDto;
import com.eggcoach.infrastructure.account.entity.UserEntity;
import com.eggcoach.infrastructure.board.entity.CategoryBoardEntity;
import com.eggcoach.infrastructure.board.repository.CategoryBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryBoardInfraService{

	private final CategoryBoardRepository categoryBoardRepository;

	public CategoryBoardEntity saveCategory(CategoryDto categoryDto, UserEntity userEntity) {
		CategoryBoardEntity entity = new CategoryBoardEntity();
		entity.setCategoryName(categoryDto.getCategoryName());
		entity.setDescription(categoryDto.getDescription());
		entity.setDisplayOrder(categoryDto.getDisplayOrder());
		entity.setUseYn(categoryDto.getUseYn() != null ? categoryDto.getUseYn() : "Y");
		entity.setCreatorId(userEntity.getUserSeq().toString());
		entity.setCreateAt(LocalDateTime.now());
		entity.setUpdateAt(LocalDateTime.now());

		return categoryBoardRepository.save(entity);
	}

	public CategoryBoardEntity updateCategory(CategoryDto categoryDto) {
		CategoryBoardEntity entity = categoryBoardRepository.findByCategoryBoardSeq(categoryDto.getCategorySeq()).orElseThrow();

		if (categoryDto.getCategoryName() != null) {
			entity.setCategoryName(categoryDto.getCategoryName());
		}
		if (categoryDto.getDescription() != null) {
			entity.setDescription(categoryDto.getDescription());
		}
		if (categoryDto.getDisplayOrder() != null) {
			entity.setDisplayOrder(categoryDto.getDisplayOrder());
		}
		if (categoryDto.getUseYn() != null) {
			entity.setUseYn(categoryDto.getUseYn());
		}
		entity.setUpdateAt(LocalDateTime.now());

		return categoryBoardRepository.save(entity);
	}

	public void deleteCategory(Long categoryId) {
		categoryBoardRepository.deleteById(categoryId);
	}

	public Optional<CategoryBoardEntity> getCategoryById(Long categoryId) {
		return categoryBoardRepository.findByCategoryBoardSeq(categoryId);
	}

	public List<CategoryBoardEntity> getAllCategories() {
		return categoryBoardRepository.findAll();
	}

	public Page<CategoryBoardEntity> getAllCategories(Pageable pageable) {
		return categoryBoardRepository.findAll(pageable);
	}

	public boolean existsCategory(Long categoryId) {
		return categoryBoardRepository.existsById(categoryId);
	}
}