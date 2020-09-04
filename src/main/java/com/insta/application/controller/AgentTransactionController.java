package com.insta.application.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insta.application.model.Agent;

//new comment

@RestController
@RequestMapping("insta/agent/")
public class AgentTransactionController 
{
	@GetMapping("transaction/{username}")
	public String returnAgentsTransactions(Pageable pageable) 
	{
		return "{\n" + 
				"    \"id\": [\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"BHASKARARAO K\",\n" + 
				"            \"Phone No.\": \"9505707814\",\n" + 
				"            \"Unique Identification No.\": \"AP30G9730\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"11/7/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00010373\",\n" + 
				"            \"Commission\": \"180\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"J PRABHAKAR RAO\",\n" + 
				"            \"Phone No.\": \"6304755194\",\n" + 
				"            \"Unique Identification No.\": \"AP31DD1021\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"12/18/19\",\n" + 
				"            \"Total Amount\": \"6761\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P112203942\",\n" + 
				"            \"Commission\": \"542\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"J PRABHAKAR RAO\",\n" + 
				"            \"Phone No.\": \"6304755194\",\n" + 
				"            \"Unique Identification No.\": \"AP04X8871\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"12/30/19\",\n" + 
				"            \"Total Amount\": \"52066\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P112664087\",\n" + 
				"            \"Commission\": \"1477\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"MUNA SAHU\",\n" + 
				"            \"Phone No.\": \"9620159222\",\n" + 
				"            \"Unique Identification No.\": \"OR07Q7133\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"11/28/19\",\n" + 
				"            \"Total Amount\": \"945\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P111379229\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"P SUNIL KUMAR\",\n" + 
				"            \"Phone No.\": \"9182587883\",\n" + 
				"            \"Unique Identification No.\": \"AP30R0162\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/14/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00009414\",\n" + 
				"            \"Commission\": \"108\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"S PADMANABHAM\",\n" + 
				"            \"Phone No.\": \"7075136209\",\n" + 
				"            \"Unique Identification No.\": \"OD07F9814\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"11/27/19\",\n" + 
				"            \"Total Amount\": \"945\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P111329329\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"BALAKRISHNA ROTHIYA\",\n" + 
				"            \"Phone No.\": \"8125757152\",\n" + 
				"            \"Unique Identification No.\": \"AP30X3638\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"12/24/19\",\n" + 
				"            \"Total Amount\": \"7904\",\n" + 
				"            \"Insurer Company\": \"FUTURE GENERALI\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"V6830161\",\n" + 
				"            \"Commission\": \"2400\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"BHUJANGA RAO TALAGANA\",\n" + 
				"            \"Phone No.\": \"7337220566\",\n" + 
				"            \"Unique Identification No.\": \"AP30X8381\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"11/13/19\",\n" + 
				"            \"Total Amount\": \"7514\",\n" + 
				"            \"Insurer Company\": \"TATA\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"160531920\",\n" + 
				"            \"Commission\": \"1885\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"BUKTA DOMBURU\",\n" + 
				"            \"Phone No.\": \"8658519221\",\n" + 
				"            \"Unique Identification No.\": \"AP30Y6891\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"12/31/19\",\n" + 
				"            \"Total Amount\": \"7904\",\n" + 
				"            \"Insurer Company\": \"FUTURE GENERALI\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"V6850272\",\n" + 
				"            \"Commission\": \"2400\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"CHINA VENKATAIAH KALLURI\",\n" + 
				"            \"Phone No.\": \"9491125649\",\n" + 
				"            \"Unique Identification No.\": \"AP30TA4061\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"12/4/19\",\n" + 
				"            \"Total Amount\": \"37488\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1831-00000951\",\n" + 
				"            \"Commission\": \"8962\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"DALLI PITAMBARA\",\n" + 
				"            \"Phone No.\": \"9701767524\",\n" + 
				"            \"Unique Identification No.\": \"OD07B5076\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/30/19\",\n" + 
				"            \"Total Amount\": \"945\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P110072415\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"DANAYYA T\",\n" + 
				"            \"Phone No.\": \"9000901876\",\n" + 
				"            \"Unique Identification No.\": \"AP30AB6249\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"1/1/20\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00012593\",\n" + 
				"            \"Commission\": \"180\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"DASARI SIMHACHAL PATRO\",\n" + 
				"            \"Phone No.\": \"8093445926\",\n" + 
				"            \"Unique Identification No.\": \"AP30V5926\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"11/29/19\",\n" + 
				"            \"Total Amount\": \"48710\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1831-00000918\",\n" + 
				"            \"Commission\": \"5868\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"DIVAKAR REDDY B\",\n" + 
				"            \"Phone No.\": \"7780225197\",\n" + 
				"            \"Unique Identification No.\": \"AP30J0026\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"9/24/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00008287\",\n" + 
				"            \"Commission\": \"104\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"DURLABH MAHARANA\",\n" + 
				"            \"Phone No.\": \"9777023682\",\n" + 
				"            \"Unique Identification No.\": \"OD07J0075\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/16/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P109425462\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"DURLABH MAHARANA\",\n" + 
				"            \"Phone No.\": \"9390359909\",\n" + 
				"            \"Unique Identification No.\": \"OD07D8156\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"1/14/20\",\n" + 
				"            \"Total Amount\": \"945\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P113283013\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"DURLABH MAHARANA\",\n" + 
				"            \"Phone No.\": \"9777023682\",\n" + 
				"            \"Unique Identification No.\": \"OD07U9926\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"1/23/20\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P113706149\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"DURLABHA MAHARANA\",\n" + 
				"            \"Phone No.\": \"9777023682\",\n" + 
				"            \"Unique Identification No.\": \"OD07A7490\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/16/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P109422712\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"DURYODHANA L\",\n" + 
				"            \"Phone No.\": \"9393392421\",\n" + 
				"            \"Unique Identification No.\": \"AP30R1872\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"11/14/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00010730\",\n" + 
				"            \"Commission\": \"180\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"E JANAKI RAO\",\n" + 
				"            \"Phone No.\": \"9471575263\",\n" + 
				"            \"Unique Identification No.\": \"AP30AA2823\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/2/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00008758\",\n" + 
				"            \"Commission\": \"108\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"GURUNATH SAHU\",\n" + 
				"            \"Phone No.\": \"9030827835\",\n" + 
				"            \"Unique Identification No.\": \"OD07L7924\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"8/30/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P106857220\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"GUVVADA ESWAR RAO\",\n" + 
				"            \"Phone No.\": \"9603883307\",\n" + 
				"            \"Unique Identification No.\": \"AP30AL7887\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"1/23/20\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00013491\",\n" + 
				"            \"Commission\": \"180\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"J NAGESWAR RAO\",\n" + 
				"            \"Phone No.\": \"9666836314\",\n" + 
				"            \"Unique Identification No.\": \"OR07D5629\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"9/4/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P107018250\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"JITENDRA MAHARANA\",\n" + 
				"            \"Phone No.\": \"9777858847\",\n" + 
				"            \"Unique Identification No.\": \"OR07T8497\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/17/19\",\n" + 
				"            \"Total Amount\": \"945\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P109521260\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"K PAUL DEVA PRASAD\",\n" + 
				"            \"Phone No.\": \"9290870559\",\n" + 
				"            \"Unique Identification No.\": \"AP30AC2817\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"1/13/20\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00013115\",\n" + 
				"            \"Commission\": \"180\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"K TEJA\",\n" + 
				"            \"Phone No.\": \"9505707814\",\n" + 
				"            \"Unique Identification No.\": \"AP30H7567\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"11/7/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00010370\",\n" + 
				"            \"Commission\": \"180\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"KAPPA MANIKYAM\",\n" + 
				"            \"Phone No.\": \"7036492961\",\n" + 
				"            \"Unique Identification No.\": \"AP30AK8140\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/17/19\",\n" + 
				"            \"Total Amount\": \"945\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00009598\",\n" + 
				"            \"Commission\": \"108\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"KRUSHNA MOHANTY\",\n" + 
				"            \"Phone No.\": \"9177968577\",\n" + 
				"            \"Unique Identification No.\": \"AP30K7220\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"12/28/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00012476\",\n" + 
				"            \"Commission\": \"180\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"KUNJU PRADHANO KUNJU\",\n" + 
				"            \"Phone No.\": \"9439419013\",\n" + 
				"            \"Unique Identification No.\": \"AP31TA9263\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/26/19\",\n" + 
				"            \"Total Amount\": \"24480\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P109961973\",\n" + 
				"            \"Commission\": \"493\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"MADHAVA RAO E\",\n" + 
				"            \"Phone No.\": \"8885889888\",\n" + 
				"            \"Unique Identification No.\": \"OR07V3699\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"2/5/20\",\n" + 
				"            \"Total Amount\": \"47013\",\n" + 
				"            \"Insurer Company\": \"TATA\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"160724688\",\n" + 
				"            \"Commission\": \"0\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"MATTA PITHAMBERAM MATTA\",\n" + 
				"            \"Phone No.\": \"8018663533\",\n" + 
				"            \"Unique Identification No.\": \"AP30W1727\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"11/26/19\",\n" + 
				"            \"Total Amount\": \"17696\",\n" + 
				"            \"Insurer Company\": \"TATA\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"160567530\",\n" + 
				"            \"Commission\": \"4676\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"MEGHANADA BEHERA\",\n" + 
				"            \"Phone No.\": \"8018336268\",\n" + 
				"            \"Unique Identification No.\": \"OD07Q7057\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"1/24/20\",\n" + 
				"            \"Total Amount\": \"945\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P113750790\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"MUNAKALA.MOHAN REDDY\",\n" + 
				"            \"Phone No.\": \"9441757116\",\n" + 
				"            \"Unique Identification No.\": \"AP16TY8568\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/9/19\",\n" + 
				"            \"Total Amount\": \"52554\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P109013998\",\n" + 
				"            \"Commission\": \"1527\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"NANDIKA KOTESH\",\n" + 
				"            \"Phone No.\": \"9381516109\",\n" + 
				"            \"Unique Identification No.\": \"AP30AL3474\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"12/16/19\",\n" + 
				"            \"Total Amount\": \"945\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00011981\",\n" + 
				"            \"Commission\": \"180\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"NARAYANAMURTHY PALATIRDHAI\",\n" + 
				"            \"Phone No.\": \"9121997114\",\n" + 
				"            \"Unique Identification No.\": \"AP30P5070\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"11/27/19\",\n" + 
				"            \"Total Amount\": \"568\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00011190\",\n" + 
				"            \"Commission\": \"109\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"NOWPADA UMAPATHI\",\n" + 
				"            \"Phone No.\": \"9000681937\",\n" + 
				"            \"Unique Identification No.\": \"AP30AL3664\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/15/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00009451\",\n" + 
				"            \"Commission\": \"108\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"PADMA CHARAN BEHERA\",\n" + 
				"            \"Phone No.\": \"7064598223\",\n" + 
				"            \"Unique Identification No.\": \"OD07C8332\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"11/22/19\",\n" + 
				"            \"Total Amount\": \"945\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P111126796\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"PADMANABHAM L\",\n" + 
				"            \"Phone No.\": \"9238592997\",\n" + 
				"            \"Unique Identification No.\": \"AP30AD3105\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"9/23/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00005628\",\n" + 
				"            \"Commission\": \"0\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"PANDABA RAJU\",\n" + 
				"            \"Phone No.\": \"9090013472\",\n" + 
				"            \"Unique Identification No.\": \"OD07M3293\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"12/13/19\",\n" + 
				"            \"Total Amount\": \"945\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P112017170\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"PARRI TATAYYA\",\n" + 
				"            \"Phone No.\": \"9618866532\",\n" + 
				"            \"Unique Identification No.\": \"AP30TA2443\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"1/31/20\",\n" + 
				"            \"Total Amount\": \"7904\",\n" + 
				"            \"Insurer Company\": \"FUTURE GENERALI\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"V6936394\",\n" + 
				"            \"Commission\": \"2400\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"PRAKASH MAHARANA\",\n" + 
				"            \"Phone No.\": \"9030686424\",\n" + 
				"            \"Unique Identification No.\": \"AP30TB1652\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"1/20/20\",\n" + 
				"            \"Total Amount\": \"5974\",\n" + 
				"            \"Insurer Company\": \"TATA\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"0159320430 01\",\n" + 
				"            \"Commission\": \"0\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"PURUSHOTHAM R\",\n" + 
				"            \"Phone No.\": \"9550527539\",\n" + 
				"            \"Unique Identification No.\": \"AP30AQ0547\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"1/28/20\",\n" + 
				"            \"Total Amount\": \"945\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00013707\",\n" + 
				"            \"Commission\": \"180\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"RAMBABU B\",\n" + 
				"            \"Phone No.\": \"7660045515\",\n" + 
				"            \"Unique Identification No.\": \"AP30AG0466\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"1/4/20\",\n" + 
				"            \"Total Amount\": \"1467\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00012753\",\n" + 
				"            \"Commission\": \"270\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"RAVI SAHU GUDIYA\",\n" + 
				"            \"Phone No.\": \"8074566489\",\n" + 
				"            \"Unique Identification No.\": \"AP30X7869\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/28/19\",\n" + 
				"            \"Total Amount\": \"7514\",\n" + 
				"            \"Insurer Company\": \"TATA\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"160490006\",\n" + 
				"            \"Commission\": \"1884\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"SRIKANTH KUMAR DASH\",\n" + 
				"            \"Phone No.\": \"9090009595\",\n" + 
				"            \"Unique Identification No.\": \"AP07TT7999\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"11/8/19\",\n" + 
				"            \"Total Amount\": \"53371\",\n" + 
				"            \"Insurer Company\": \"TATA\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"160523998\",\n" + 
				"            \"Commission\": \"6965\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"SURYANARAYANA M\",\n" + 
				"            \"Phone No.\": \"8297009580\",\n" + 
				"            \"Unique Identification No.\": \"AP30K3713\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"9/23/19\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"BAJAJ\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"OG-20-1804-1806-00005625\",\n" + 
				"            \"Commission\": \"0\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"SUSANT KUMAR PADHY\",\n" + 
				"            \"Phone No.\": \"9390402687\",\n" + 
				"            \"Unique Identification No.\": \"OD18A3442\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"1/11/20\",\n" + 
				"            \"Total Amount\": \"946\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P113192971\",\n" + 
				"            \"Commission\": \"59\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"TUNA GOUDA\",\n" + 
				"            \"Phone No.\": \"9178178117\",\n" + 
				"            \"Unique Identification No.\": \"AP16Y7515\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"11/22/19\",\n" + 
				"            \"Total Amount\": \"54601\",\n" + 
				"            \"Insurer Company\": \"TATA\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"160558160\",\n" + 
				"            \"Commission\": \"7115\"\n" + 
				"        }\n" + 
				"    ]\n" + 
				"}";
	}
	
	@GetMapping("comissionreport/{username}")
		public String returnAgentsComossion(Pageable pageable) 
		{
			return "{\n" + 
					"    \"Comission\": [\n" + 
					"        {\n" + 
					"            \"Month\": \"Aug-19\",\n" + 
					"            \"Total No of Policies\": \"1\",\n" + 
					"            \"Total Policy Amount\": \"946\",\n" + 
					"            \"Total Comission\": \"59\"\n" + 
					"        },\n" + 
					"        {\n" + 
					"            \"Month\": \"Sep-19\",\n" + 
					"            \"Total No of Policies\": \"4\",\n" + 
					"            \"Total Policy Amount\": \"3784\",\n" + 
					"            \"Total Comission\": \"163\"\n" + 
					"        },\n" + 
					"        {\n" + 
					"            \"Month\": \"Oct-19\",\n" + 
					"            \"Total No of Policies\": \"11\",\n" + 
					"            \"Total Policy Amount\": \"92113\",\n" + 
					"            \"Total Comission\": \"4572\"\n" + 
					"        },\n" + 
					"        {\n" + 
					"            \"Month\": \"Nov-19\",\n" + 
					"            \"Total No of Policies\": \"12\",\n" + 
					"            \"Total Policy Amount\": \"188133\",\n" + 
					"            \"Total Comission\": \"27335\"\n" + 
					"        },\n" + 
					"        {\n" + 
					"            \"Month\": \"Dec-19\",\n" + 
					"            \"Total No of Policies\": \"8\",\n" + 
					"            \"Total Policy Amount\": \"114959\",\n" + 
					"            \"Total Comission\": \"16200\"\n" + 
					"        },\n" + 
					"        {\n" + 
					"            \"Month\": \"Jan-20\",\n" + 
					"            \"Total No of Policies\": \"11\",\n" + 
					"            \"Total Policy Amount\": \"22910\",\n" + 
					"            \"Total Comission\": \"3626\"\n" + 
					"        },\n" + 
					"        {\n" + 
					"            \"Month\": \"Feb-20\",\n" + 
					"            \"Total No of Policies\": \"1\",\n" + 
					"            \"Total Policy Amount\": \"47013\",\n" + 
					"            \"Total Comission\": \"0\"\n" + 
					"        }\n" + 
					"    ]\n" + 
					"}";
		}

}
