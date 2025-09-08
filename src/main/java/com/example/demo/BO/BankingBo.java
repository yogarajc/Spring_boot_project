package com.example.demo.BO;

import com.example.demo.DTO.BankingDto;
import com.example.demo.DTO.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


@Component
public class BankingBo {
    private static final Logger log = LoggerFactory.getLogger(BankingBo.class);
    HashMap<String, Object> AccountLedger = new HashMap<>();

    public ResponseDTO CreateAccount(BankingDto bankingDto) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (AccountLedger.containsKey(bankingDto.getAccountId())) {
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Account Id must be Unique");
                log.info("Response : Account Id must be Unique");
                return responseDTO;
            } else if (bankingDto.getBalance() < 0) {
                log.info("Account balance " + bankingDto.getBalance());
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Balance cannot be lesser than zero");
                log.info("Response : Balance cannot be lesser than zero");
            } else if (!bankingDto.getAccountHolderName().matches("^[a-zA-Z ]+$")) {
                log.info("Account Name " + bankingDto.getAccountHolderName());
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("Account holder name cannot have special characters and digits");
                log.info("Response : Account holder name cannot have special characters and digits");
            } else {
                AccountLedger.put(bankingDto.getAccountId(), bankingDto);
                responseDTO.setStatus("Success");
                log.info("Response : Success");
                responseDTO.setMessage("Account Added successfully");
            }
            return responseDTO;
        } catch (Exception exc) {
            log.info("Exception : " + exc);
            responseDTO.setStatus("Failure");
            responseDTO.setMessage("Exception :" + exc.getMessage());
            log.info("Response Exception : "+ exc.getMessage());
            return responseDTO;
        }
    }

    public Object getAllAccounts() {
        ResponseDTO responseDTO = new ResponseDTO();
        if (AccountLedger.isEmpty()) {
            responseDTO.setStatus("Failure");
            responseDTO.setMessage("No Accounts Available");
            log.info("Response : No Accounts Available");
            return responseDTO;
        }
        List<Object> allHolderDetails = new ArrayList<Object>();
            for (Map.Entry<String, Object> HolderDetails : AccountLedger.entrySet()) {
                allHolderDetails.add(HolderDetails.getValue());
            }
            return allHolderDetails;
    }

    public Object getUserbyId(String id){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (AccountLedger.isEmpty()){
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("No Accounts Available");
                log.info("Respones : No Accounts Available");
                return responseDTO;
            }
            else if (!AccountLedger.containsKey(id)){
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("No Accounts Available for the id :" +id);
                log.info("Respones : No Accounts Available for the id :" +id);
                return responseDTO;
            }
            else {
                log.info("Response : Success");
                return AccountLedger.get(id);
            }
        } catch (Exception exc){
            responseDTO.setStatus("Failure");
            responseDTO.setMessage("Exception : "+exc.getMessage());
            log.info("Response : Exception "+ exc.getMessage());
            return responseDTO;
        }
    }

    public ResponseDTO deleteAccountbyId(String id){
        ResponseDTO responseDTO = new ResponseDTO();
        if (AccountLedger.isEmpty()){
            responseDTO.setStatus("Failure");
            responseDTO.setMessage("No Accounts Available");
            log.info("Respones : No Accounts Available");
            return responseDTO;
        }
        else if (!AccountLedger.containsKey(id)){
            responseDTO.setStatus("Failure");
            responseDTO.setMessage("No Accounts Available for the id :" +id);
            log.info("Respones : No Accounts Available for the id :" +id);
            return responseDTO;
        }
        else {
            log.info("Response : Account deleted for id "+id);
            AccountLedger.remove(id);
            responseDTO.setStatus("Success");
            responseDTO.setMessage("Account deleted successfully");
            return responseDTO;
        }
    }

    public ResponseDTO deposit(String id, double amount){
        BankingDto bankingDto = new BankingDto();
        ResponseDTO responseDTO = new ResponseDTO();
       if (!AccountLedger.containsKey(id)){
            responseDTO.setStatus("Failure");
            responseDTO.setMessage("No Accounts Available for the id :" +id);
            log.info("Respones : No Accounts Available for the id :" +id);
            return responseDTO;
        }
        else if(amount <= 0){
            responseDTO.setStatus("Failure");
            responseDTO.setMessage("deposit amount should be higher than 0");
            log.info("Entered amount :"+amount);
            log.info("Respones : deposit amount should be higher than 0");
        }
        else {
            bankingDto = (BankingDto) AccountLedger.get(id);
            bankingDto.setBalance(bankingDto.getBalance() + amount);
            AccountLedger.put(id,bankingDto);
            log.info("Amount Updated for Id "+id +"Amount "+amount);
            responseDTO.setStatus("Success");
            responseDTO.setMessage("Deposit Successfully done, Current Balance "+bankingDto.getBalance());
        }
        return responseDTO;
    }

    public ResponseDTO withdrawal(String id,double amount){
        BankingDto bankingDto = new BankingDto();
        ResponseDTO responseDTO = new ResponseDTO();
        if (AccountLedger.containsKey(id)){
            bankingDto = (BankingDto) AccountLedger.get(id);
            if (bankingDto.getBalance() <= 0){
                log.info("Response : No availabe balance for withdrawal");
                responseDTO.setStatus("Failure");
                responseDTO.setMessage("No availabe balance for withdrawal");
            }
            bankingDto.setBalance(bankingDto.getBalance() - amount);
            AccountLedger.put(id,bankingDto);
            log.info("Response : Success ");
            responseDTO.setStatus("Success");
            responseDTO.setMessage("Withdrawal Success : Current balance "+bankingDto.getBalance());
        }
        else {
            responseDTO.setStatus("Failure");
            responseDTO.setMessage("Account Not available for Id "+id);
        }

        return responseDTO;
    }

}

