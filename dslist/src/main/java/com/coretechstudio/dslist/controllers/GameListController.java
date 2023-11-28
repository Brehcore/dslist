package com.coretechstudio.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coretechstudio.dslist.entities.dto.GameListDTO;
import com.coretechstudio.dslist.entities.dto.GameMinDTO;
import com.coretechstudio.dslist.entities.dto.ReplacementDTO;
import com.coretechstudio.dslist.services.GameListService;
import com.coretechstudio.dslist.services.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {
	
	@Autowired
	private GameListService gameListService;
	
	@Autowired
	private GameService gameService;
	
	
	@GetMapping
	public List<GameListDTO> findAll() {
		List<GameListDTO> result = gameListService.findAll();
		return result;
	}
	
	@GetMapping(value = "/{listId}/games")
	public List<GameMinDTO> findByList(@PathVariable Long listId) {
		List<GameMinDTO> result = gameService.findByList(listId);
		return result;
	}
	
	@PostMapping(value = "/{listId}/replacement")
	public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
	    //Verifica se a propriedade destinationIndex é válida
	    if (body.getDestinationIndex() != null && body.getDestinationIndex() >= 0) {
	      gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
	    } else {
	      //Tratar o caso em que a propriedade destinationIndex é inválida
	    }
	  }	

}
