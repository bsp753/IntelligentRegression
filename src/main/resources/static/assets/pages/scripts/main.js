var jsondata=[];
var customerid;
var quoteid;
var vehicleid;
var transactionid;
var refpaymentid;
$(document).ready(function() {
	console.log(document);
	document.getElementById("myForm").style.display = "none";

	$("#updateCustomerSubmit").click(function() {
		prepareRequestBody(customerid);
	});

	$("#updatevehicleSubmit").click(function() {
		prepareRequestBody(vehicleid);
	});

	$("#updatequoteSubmit").click(function() {
		prepareRequestBody(quoteid);
	});
	
	$("#updateRefPaymentSubmit").click(function() {
		console.log("huhahaaaaaaaa****")
		prepareRequestBody(refpaymentid);
	});

	$("#showData").on('click','.Downloadtransactionid',function(){
		console.log('Sibashrit');
		var selectedRecord=$(this).attr("id");
		console.log("Selected Record id: "+selectedRecord);
		downloadRecord(selectedRecord);

	});

	$("#showData").on('click','.sendemail',function(){
		console.log("kkkkk:"+$(this).closest('tr').find('td'));
		console.log("kkkkk:"+$(this).closest('tr').find('td').text);
	})

	$("#showData").on('click','#Deletequoteid',function(){
		var selectedRecord=$(this).closest('tr').find('td').find('a').text();
		selectedRecord=selectedRecord.replace("Delete","").trim()
		confirmDeleteRecord(selectedRecord);

	});

	$("#showData").on('click','#Deletecustid',function(){
		var selectedRecord=$(this).closest('tr').find('td').find('a').text();
		selectedRecord=selectedRecord.replace("Delete","").trim();
		confirmDeleteRecord(selectedRecord);
	});

	$("#showData").on('click','#Deletetransactionid',function(){
		var selectedRecord=$(this).closest('tr').find('td').find('a').text();
		selectedRecord=selectedRecord.replace("Delete","").trim();
		selectedRecord=selectedRecord.replace("Download","").trim();
		confirmDeleteRecord(selectedRecord);

	});

	$("#showData").on('click','#Deletevehicleid',function(){
		var selectedRecord=$(this).closest('tr').find('td').find('a').text();
		selectedRecord=selectedRecord.replace("Delete","").trim();
		confirmDeleteRecord(selectedRecord);
	});
	
	$("#showData").on('click','#Deleterefpaymentid',function(){
		var selectedRecord=$(this).closest('tr').find('td').find('a').text();
		selectedRecord=selectedRecord.replace("Delete","").trim();
		confirmDeleteRecord(selectedRecord);
	});
	


	$("#showData").on('click','#quoteid',function(){
		document.getElementById("myForm").style.display = "block";
		var selectedRecord=$(this).text();
		for(var k=0;k<jsondata.length;k++){
			if(jsondata[k].quoteid==selectedRecord){
				quoteid=selectedRecord;
				$("#updatedquoteamount").val(jsondata[k].amount);
				$("#updatedquotedescription").val(jsondata[k].description);
				$("#updatedquoteidv").val(jsondata[k].idv);
				$("#updatedquotepackage").val(jsondata[k].packagetype);
				$("#updatequotedate").val(jsondata[k].quote_date);
				$("#updatedquotestatus").val(jsondata[k].quote_status);
				$("#customerupdatedclient_company").val(jsondata[k].insurercompany);
			}
		}
	});

	$("#showData").on('click','#transactionid',function(){
		document.getElementById("myForm").style.display = "block";
		var selectedRecord=$(this).text();
		for(var k=0;k<jsondata.length;k++){
			if(jsondata[k].paymentid==selectedRecord){
				transactionid=selectedRecord;
				$("#updated_paidamount").val(jsondata[k].amountpaid);
				$("#updated_commission").val(jsondata[k].commission);
				$("#updated_dueamount").val(jsondata[k].dueamount);
				$("#updated_insurer_company").val(jsondata[k].insurer_company);
				$("#updated_paymentdate").val(jsondata[k].paymentDate);
				$("#updated_expirydate").val(jsondata[k].expiryDate);
				$("#updated_payment_status").val(jsondata[k].payment_status);
				$("#updatedquoteamount").val(jsondata[k].paymentid);
				$("#updated_paymentmode").val(jsondata[k].paymentmode);
				$("#updated_policyno").val(jsondata[k].policyno);
				$("#updated_rebate").val(jsondata[k].rebate);
				$("#updated_totalamount").val(jsondata[k].totalamount);
			}
		}
	});
	
	$("#showData").on('click','#refpaymentid',function(){
		document.getElementById("myForm").style.display = "block";
		console.log("hehehee: "+jsondata)
		var selectedRecord=$(this).text();
		for(var k=0;k<jsondata.length;k++){
			if(jsondata[k].id==selectedRecord){
				refpaymentid=selectedRecord;
				$("#customerupdatedname").val(jsondata[k].referrerName);
				$("#updatedpaymentdate").val(jsondata[k].payment_date);
				$("#updatedpaymentamount").val(jsondata[k].amount);
			}
		}
	});


	$("#showData").on('click','#vehicleid',function(){
		document.getElementById("myForm").style.display = "block";
		var selectedRecord=$(this).text();
		for(var k=0;k<jsondata.length;k++){
			if(jsondata[k].vehicle_id==selectedRecord){
				vehicleid=selectedRecord;
				$("#updatedcustomerId").val(jsondata[k].vehicle_id);
				$("#updatedregistration_no").val(jsondata[k].registration_number);
				$("#updatedmodel").val(jsondata[k].model_number);
				$("#updatedexpiry_date").val(jsondata[k].expiry_date);
			}
		}
	});


	$("#showData").on('click','#custid',function(){
		document.getElementById("myForm").style.display = "block";
		var selectedRecord=$(this).text();
		for(var k=0;k<jsondata.length;k++){
			if(jsondata[k].customerid==selectedRecord){
				customerid=selectedRecord;
				$("#customerupdatedacquisition_status").val(jsondata[k].acquisition_status);
				$("#customerupdatedname").val(jsondata[k].name);
				$("#customerupdatedphone_no").val(jsondata[k].phone_no);
				$("#customerupdatedemail").val(jsondata[k].email);
				$("#customerupdatedreferer").val(jsondata[k].referer);

			}
		}
	});

});

function setRole(role) {
	if (role == "admin") {
		sessionStorage.setItem("userrole", "admin");
		console.log("User Role is:"+role);
	} else {
		sessionStorage.setItem("userrole", "nonadmin");
		console.log("User role is:"+role);
	}
}

function getRole() {
	console.log("Role returned is:"+isAdmin)
	return window.isAdmin;
}

function getCookie() {
	var cook = document.cookie;
	var cooks = [];
	var tokenValue;
	cooks = cook.split(";")
	for (var i = 0; i < cooks.length; i++) {
		console.log("Cookie..")
		console.log(cooks[i])
		if (cooks[i].includes("bearerToken")) {
			tokenValue = cooks[i].split("=")[1].trim();
			console.log("Token value is:")
			console.log(tokenValue)
			tokenValue="Bearer "+tokenValue;
		}
	}
	return tokenValue;
}

function confirmDeleteRecord(selectedRecord){
	var msg=confirm("Do you want to delete the record!")
	if(msg==true){
		apiDeleteCall(selectedRecord);
	}
	else{
		console.log("Dont delete the record..")
	}
}

function callupdateTransaction(formData){
	prepareRequestBody(formData,transactionid);
}

function updateamount(){
	$("#dueamount").val("");
	var totalamount=$("#totalamount").val();
	var paidamount=$("#paidamount").val();
	dueamount=totalamount-paidamount;
	if(totalamount == null || totalamount ==""){
		$("#dueamount").val("");
	}
	else{
		$("#dueamount").val(dueamount);
	}
}

function updatepaymentstatus(){
	var dueamount=$("#dueamount").val();
	if(dueamount==0){
		$("#payment_status").val("Complete")
	}
	else{
		$("#payment_status").val("Due")
	}

}

function closeForm() {
	document.getElementById("myForm").style.display = "none";
}


function CreateTableFromJSON(respjson,filterid,policyDownload) {
	var flag=false;
	jsondata=respjson;
	var downloadlinkindex;
	var col = [];
	var k=0;
	for (var i = 0; i < respjson.length; i++) {
		for (var key in respjson[i]) {
			if (col.indexOf(key) === -1) {
				console.log("Value is:"+key)
				if(key == "fileUuid" || key == "fileUUID"){
					flag=true;
					downloadlinkindex=k;
				}
				col.push(key);
			}
			k=k+1;
		}
	}

	// CREATE DYNAMIC TABLE.
	var table = document.createElement("table");
	// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.
	table.setAttribute("class","sub");
	var tr = table.insertRow(-1);                   // TABLE ROW.
	var updateId;
	for (var i = 0; i <=col.length; i++) {
		var th = document.createElement("th");      // TABLE HEADER.
		if(i==col.length){
			if(policyDownload){
				th.innerHTML = "Send Email";
				tr.appendChild(th);
			}
			else{
				th.innerHTML = "Delete Record";
				tr.appendChild(th);
			}
		}
		else{
			th.innerHTML = col[i];
			tr.appendChild(th);
		}
	}


	// ADD JSON DATA TO THE TABLE AS ROWS.
	for (var i = 0; i < respjson.length; i++) {
		var fileExists=true;
		tr = table.insertRow(-1);
		var value=col[j];
		for (var j = 0; j <=col.length; j++) {
			if(j==0){
				if(policyDownload){
					var tabCell = tr.insertCell(-1);
					updateId=respjson[i][col[j]];
					tabCell.innerHTML = respjson[i][col[j]];
				}
				else{
					var tabCell = tr.insertCell(-1);
					updateId=respjson[i][col[j]];
					tabCell.innerHTML = "<a href=\"#\" id=\""+filterid+"\">"+respjson[i][col[j]];
				}
			}else if(flag && j==downloadlinkindex){
				var tabCell = tr.insertCell(-1);
				console.log("Download file step...")
				console.log(respjson[i][col[j]])
				if(respjson[i][col[j]]){
					tabCell.innerHTML = "<a href=\"#\" class=\"Download"+filterid+"\"" + "id=\""+respjson[i][col[j]]+"\">"+"Download"
				}
				else{
					console.log("No File exists...")
					fileExists=false;
					console.log("Ufufuf: "+fileExists)
					tabCell.innerHTML = "<a href=\"#\" id=\"Download"+filterid+"\">"+"No File"
				}
			}else if(j==col.length){
				if(policyDownload){
					var tabCell = tr.insertCell(-1);
					console.log("File exists or not:"+fileExists)
					if(fileExists){
						tabCell.innerHTML = "<input type=\"button\" value=\"Send Email\" class=\"sendemail\" id=\""+respjson[i][col[j]]+"\">";
					}
					else{
						tabCell.innerHTML = "<input type=\"button\" disabled value=\"Send Email\" class=\"sendemail\" id=\""+respjson[i][col[j]]+"\">";
					}
				}
				else{
					var tabCell = tr.insertCell(-1);
					tabCell.innerHTML = "<a href=\"#\" id=\"Delete"+filterid+"\">"+"Delete"	
				}
			}
			else{
				var tabCell = tr.insertCell(-1);
				tabCell.innerHTML = respjson[i][col[j]];    

			}
		}
	}

	// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
	var divContainer = document.getElementById("showData");
	divContainer.innerHTML = "";
	divContainer.appendChild(table);
}
