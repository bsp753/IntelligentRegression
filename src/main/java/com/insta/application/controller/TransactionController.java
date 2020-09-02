/*
 * package com.insta.application.controller;
 * 
 * import java.util.ArrayList;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.ResponseStatus; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.insta.application.model.Transaction; import
 * com.insta.application.service.TransactionService;
 * 
 * @RestController
 * 
 * @RequestMapping("insta/transactions") public class TransactionController {
 * 
 * @Autowired TransactionService tServiceobj;
 * 
 * @GetMapping public ArrayList<Transaction> returnAllCustomers() { return
 * tServiceobj.findAll(); }
 * 
 * @GetMapping("/{id}") public Transaction returnSingletransaction(@PathVariable
 * int id) { return tServiceobj.findSingleTransaction(id); }
 * 
 * @PostMapping("/create")
 * 
 * @ResponseStatus(HttpStatus.CREATED) public int createVehicle(@RequestBody
 * Transaction payload){ return (tServiceobj.createTransaction(payload)); }
 * 
 * @PutMapping("/{id}") public Transaction updatetransaction(@PathVariable int
 * id, @RequestBody Transaction transaction) { return
 * tServiceobj.updateTransaction(id, transaction); }
 * 
 * @DeleteMapping(value = "/{id}") public int delete(@PathVariable int id) {
 * return tServiceobj.deleteTransaction(id); }
 * 
 * }
 */