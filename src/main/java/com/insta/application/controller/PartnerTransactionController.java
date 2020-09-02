package com.insta.application.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("insta/partner")
public class PartnerTransactionController 
{
	
	@GetMapping("transaction/{username}")
	public String returnPartnersTransactions(Pageable pageable) 
	{
		return "{\n" + 
				"    \"id\": [\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"ANJANI STONE CRUSHERS\",\n" + 
				"            \"Phone No.\": \"9959912668\",\n" + 
				"            \"Unique Identification No.\": \"AP31CF0996\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/31/19\",\n" + 
				"            \"Total Amount\": \"12209\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P110087912\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"ANJANI STONE CRUSHERS\",\n" + 
				"            \"Phone No.\": \"9959912668\",\n" + 
				"            \"Unique Identification No.\": \"AP31TM1559\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/30/19\",\n" + 
				"            \"Total Amount\": \"63448\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P110094568\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"ANJANI STONE CRUSHERS\",\n" + 
				"            \"Phone No.\": \"9959912668\",\n" + 
				"            \"Unique Identification No.\": \"AP31TM1669\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/30/19\",\n" + 
				"            \"Total Amount\": \"63448\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P110089728\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"ANJANI STONE CRUSHERS\",\n" + 
				"            \"Phone No.\": \"9959912668\",\n" + 
				"            \"Unique Identification No.\": \"AP31TN4679\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/30/19\",\n" + 
				"            \"Total Amount\": \"18392\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P110094156\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"ANJANI STONE CRUSHERS\",\n" + 
				"            \"Phone No.\": \"9959912668\",\n" + 
				"            \"Unique Identification No.\": \"AP31TN4799\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"10/30/19\",\n" + 
				"            \"Total Amount\": \"18392\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P110088810\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"ANJANI STONE CRUSHERS\",\n" + 
				"            \"Phone No.\": \"9959912668\",\n" + 
				"            \"Unique Identification No.\": \"AP31CB5999\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"12/26/19\",\n" + 
				"            \"Total Amount\": \"6443\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P112529432\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"ANJANI STONE CRUSHERS\",\n" + 
				"            \"Phone No.\": \"9959912668\",\n" + 
				"            \"Unique Identification No.\": \"CPM27122019\",\n" + 
				"            \"Insurance Type\": \"Others\",\n" + 
				"            \"Payment Date\": \"12/27/19\",\n" + 
				"            \"Total Amount\": \"19439\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502004419P112585378\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"ANJANI STONE CRUSHERS\",\n" + 
				"            \"Phone No.\": \"9959912668\",\n" + 
				"            \"Unique Identification No.\": \"CPMSCHWINGSTETTER\",\n" + 
				"            \"Insurance Type\": \"Others\",\n" + 
				"            \"Payment Date\": \"12/27/19\",\n" + 
				"            \"Total Amount\": \"7903\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502004419P112587893\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"ANJANI STONE CRUSHERS\",\n" + 
				"            \"Phone No.\": \"9959912668\",\n" + 
				"            \"Unique Identification No.\": \"AP31TE9919\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"1/2/20\",\n" + 
				"            \"Total Amount\": \"18772\",\n" + 
				"            \"Insurer Company\": \"UIIC\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"1502003119P112851520\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"ANJANI STONE CRUSHERS\",\n" + 
				"            \"Phone No.\": \"9959912668\",\n" + 
				"            \"Unique Identification No.\": \"2280026679-WC10012020\",\n" + 
				"            \"Insurance Type\": \"Others\",\n" + 
				"            \"Payment Date\": \"1/10/20\",\n" + 
				"            \"Total Amount\": \"54275\",\n" + 
				"            \"Insurer Company\": \"TATA\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"2280026679\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"            \"Customer Name\": \"ANJANI STONE CRUSHERS\",\n" + 
				"            \"Phone No.\": \"9959912668\",\n" + 
				"            \"Unique Identification No.\": \"AP39TJ5899\",\n" + 
				"            \"Insurance Type\": \"Vehicle\",\n" + 
				"            \"Payment Date\": \"2/13/20\",\n" + 
				"            \"Total Amount\": \"23442\",\n" + 
				"            \"Insurer Company\": \"TATA\",\n" + 
				"            \"Payment Status\": \"Complete\",\n" + 
				"            \"Policy No.\": \"160743370\"\n" + 
				"        }\n" + 
				"    ]\n" + 
				"}";
	}

}
