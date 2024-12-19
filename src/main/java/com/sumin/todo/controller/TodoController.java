package com.sumin.todo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumin.todo.vo.TodoVO;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("http://localhost:7930")
@RestController
@RequestMapping("/")
public class TodoController {
	
	// DB 대신 static 데이터이용 
	
	private static List<TodoVO> todoList = new ArrayList<>();
	
	@PostConstruct
	void init() {
		log.info("실행되는지 눈으로 확인");
		
		TodoVO todo = null;
		for(int i = 1; i <= 4; i++) {
			todo = new TodoVO();
			todo.setTodoId(i);
			todo.setTodoTitle("오늘 할일" +i);
			todoList.add(todo);
		}
		log.info("실행되는지 눈으로 확인 {}", todoList);
	}
	
	@GetMapping("/todos") // 리스트 조회
	public List<TodoVO> getTodos(){
		return todoList;
	}
	
	@GetMapping("/todos/{todoId}") // 리스트 조회
	public TodoVO getTodo(@PathVariable int todoId){
		for (TodoVO todoVO : todoList) {
			if(todoVO.getTodoId() == todoId) {
				return todoVO;
			}
		}
		return null; // 못찾으면 null
		
	}
	
	@PostMapping("/todos") // insert 
	public String insTodo(@RequestBody TodoVO todo) {
		todoList.add(todo);
		return "등록 성공이야";
	}
	
	@PutMapping("/todos/{todoId}") // update 
	public String updTodo(@PathVariable int todoId, @RequestBody TodoVO todo) {
		
		for (TodoVO todoVO : todoList) {
			if(todoVO.getTodoId() == todoId) {
				todoVO.setTodoTitle(todo.getTodoTitle());
				return "수정 성공이야";
			}
		}
		return "수정 안됐어";
	}
	
	
	@DeleteMapping("/todos/{todoId}") // 리스트 조회
	public String delTodo(@PathVariable int todoId){
		for (TodoVO todoVO : todoList) {
			if(todoVO.getTodoId() == todoId) {
				todoList.remove(todoVO);
				return "삭제성공";
			}
		}
		return "삭제실패"; // 못찾으면 null
		
	}
	

}
