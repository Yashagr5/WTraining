package com.company.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.company.entity.NotifiedOwner;
import com.company.repo.NotifiedOwnerRepository;

@Service
public class NotifiedOwnerService {
	 private final NotifiedOwnerRepository repo;

	    public NotifiedOwnerService(NotifiedOwnerRepository repo) {
	        this.repo = repo;
	    }

	    public NotifiedOwner saveNotification(int ownerId, String ownerName) {
	        NotifiedOwner no = new NotifiedOwner(ownerId, ownerName);
	        return repo.save(no);
	    }

	    public List<NotifiedOwner> findAll() {
	        return repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	    }
}
