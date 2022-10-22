package com.lifeway.wordCount.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.lifeway.wordCount.dto.WordCountDTO;
import com.lifeway.wordCount.exceptions.IDAlreadyUsedException;

@SpringBootTest
@ContextConfiguration(classes= {WordCountController.class})
@TestMethodOrder(OrderAnnotation.class)
public class WordCountControllerTest
{
	
	@Autowired
	private WordCountController controller;

	@Test
	public void testCountPotentialtWords_BlankString()
	{
		assertEquals(0, controller.countPotentialWords(null));
		
		assertEquals(0, controller.countPotentialWords(""));
		
		assertEquals(0, controller.countPotentialWords("    "));
		
		assertEquals(0, controller.countPotentialWords("\t"));
	}
	
	@Test
	public void testCountPotentialtWords_TwoWords()
	{
		assertEquals(2,controller.countPotentialWords("Hello World!"));
	}
	
	@Test
	@Order(1)
	public void testCountPotentialtWords_NumbersInWords_5Words()
	{
		assertEquals(5,controller.countPotentialWords("This is not a v4lid word"));
	}
	
	@Test
	@Order(2)
	public void testCountWords_5words() throws IDAlreadyUsedException
	{
		WordCountDTO wordCount = new WordCountDTO("123", "This is not a v4lid word");
		
		assertEquals(5, controller.countWords(wordCount));
	}
	
	@Test
	@Order(3)
	public void testCountWords_ID_InUse()
	{
		WordCountDTO wordCount = new WordCountDTO("123", "This is not a v4lid word");
		
		assertThrows(IDAlreadyUsedException.class, () -> controller.countWords(wordCount));
	}
	
	@Test
	@Order(4)
	public void testCountWords_5plus2() throws IDAlreadyUsedException
	{
		WordCountDTO wordCount = new WordCountDTO("456", "Jesus wept!");
		
		assertEquals(7, controller.countWords(wordCount));
	}

}
