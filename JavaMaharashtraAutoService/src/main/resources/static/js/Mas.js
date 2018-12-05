var myApp = angular.module("myApp", []);

myApp.controller("AssignmentsController", function($scope, $http) {

	$http.get("/GetAssignments").then(function(response) {
		$scope.assignmentList = response.data;

	});
});

myApp.controller("jobsController", function($scope, $http) {

	$http.get("/GetJobDetails").then(function(response) {
		debugger;
		$scope.jobsList = response.data;
		
	}).then(function(data){debugger;ConvertDates();});
});

myApp.controller("productController", function($scope, $http) {

	$http.get("/GetProducts").then(function(response) {
		$scope.productList = response.data;
	});
});

myApp.controller("offerController", function($scope, $http) {

	$http.get("/GetOffer").then(function(response) {
		$scope.offerList = response.data;
	});
});

myApp.controller("productTypeController", function($scope, $http) {

	$http.get("/GetProductTypes").then(function(response) {
		$scope.productTypeList = response.data;
	});
});

myApp.controller("usersController", function($scope, $http) {

	$http.get("/ViewAllUsers").then(function(response) {
		$scope.usersList = response.data;
	});
});

myApp.controller("rolesController", function($scope, $http) {

	$http.get("/GetRoles").then(function(response) {
		$scope.roleList = response.data;
	});
});

$(document).ready(function() {
	
	
	BindHeaderFooter();
	FillProductTypeDropDown();
	FillEngineerDropDown();
	FillJobsDropDown();
	profilepage();
	GetInvoiceData();
	FillProductDropDown();
	FillProductDropDownInventory();
	FillAllUsersDropDown();
	FillRolesDropDown();
	
	
});

function GetModelReady(ButtonId, modelID, title,url,maxWidth,maxHeight) {

	debugger;
	var myWindow = $(modelID);
	var button = $(ButtonId);

	AlignModelCenter(ButtonId, modelID);

	myWindow.kendoWindow({
		draggable : false,
		title : title,
		minWidth : "300px",
		maxWidth : maxWidth,
		maxHeight : maxHeight,
		visible : false,
		resizable : false,
		modal : true,
		content : url,

	}).data("kendoWindow");

}

function AlignModelCenter(ButtonId, modelID) {

	var myWindow = $(modelID);
	var button = $(ButtonId);
	button.click(function() {
		debugger;
		$('#errorBox').css('display', 'none');
		$('#errorMsg').text('');
		$('#userEmail').text('');
		$('#userPassword').text('');
		myWindow.data("kendoWindow").center().open();
		$(modelID).data('kendoWindow').center();
		$(modelID).closest(".k-window").css({
			position : 'fixed',
			margin : 'auto',
			top : '20%'
		});
		ControllerCall();
	});
}

function DoLogin() {
	debugger;
	var userName = $('#userEmail').val();
	var password = $('#userPassword').val();

	var obj = {
		userName : userName,
		password : password
	};

	$.ajax({
		type : "POST",
		url : "/Login",
		data : JSON.stringify(obj),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			debugger;
			if (data.email == null || data.email == "") {
				$('#errorBox').css('display', 'block');
				$('#errorMsg').text("Invalid credentials. Please try again.");
			} else {
				$('#errorBox').css('display', 'none');
				$('#errorMsg').text('');

				$('#loginModel').data("kendoWindow").close();
				sessionStorage.setItem("Email", data.email);
				sessionStorage.setItem("Role", data.role);

				CheckAuthentication();
			}

		},
		error : function(e) {
			debugger;
			console.log(e);
		}

	});
}

function FillProductTypeDropDown() {
	$.ajax({
		type : "GET",
		url : "/GetProductTypes",
		success : function(data) {
			debugger;
			$('#dropdown').kendoDropDownList({
				dataSource : data,
				dataTextField : "proType",
				dataValueField : "proTypeId",
				change : onChange,
				optionLabel : "Select Product Type"
			});

		},
		error : function(e) {
			debugger;
			console.log(e);
		}

	});

}

function BindHeaderFooter() {

	$.ajax({
		type : "GET",
		url : "/header.html",
		success : function(viewHtml) {

			$('#header').replaceWith(viewHtml);

			CheckAuthentication();
			GetModelReady("#btnLogin", "#loginModel","User Login","LoginPage.html","500px","400px");
			ViewInvoice();
			/*
			 * if (window.location.href.indexOf('index.html')==-1 &&
			 * (sessionStorage.getItem("Email") == null ||
			 * sessionStorage.getItem(""))) {
			 * window.location.href="/index.html"; return; }
			 */
		},
		error : function(e) {
			console.log(e);
		}
	});
	$.ajax({
		type : "GET",
		url : "/footer.html",
		success : function(viewHtml) {

			$('#footer').replaceWith(viewHtml);
		},
		error : function(e) {
			console.log(e);
		}
	});

}

function CheckAuthentication() {

	if (sessionStorage.getItem("Email") == null || sessionStorage.getItem("")) {

		$('#mRegistration').css('display', 'block');
		$('#mLogin').css('display', 'block');
		$('#mLogout').css('display', 'none');
		$('#mWelcome').css('display', 'none');
	} else {
		$('#mRegistration').css('display', 'none');
		$('#mLogin').css('display', 'none');
		$('#mLogout').css('display', 'block');
		$('#mWelcome').css('display', 'block');
		$('#mWelcomeLnk').text('Welcome : ' + sessionStorage.getItem("Email"));
		$('#mWelcomeLnk').attr('href','/UserProfile.html?userEmail='+ sessionStorage.getItem("Email"));
		
		CheckAuthorization();
	}
}

function CheckAuthorization() {

	if (sessionStorage.getItem("Role") == "Admin") {
		$('#mRoles').css("display", "block");
		$('#mJobs').css("display", "block");
		$('#mProdType').css("display", "block");
		$('#mProd').css("display", "block");
		$('#mEmpDetails').css("display", "block");
		$('#mOffers').css("display", "block");
		$('#mInventory').css("display", "block");
		$('#mEngAssignment').css("display", "block");
		$('#mInvoice').css("display", "block");
		$('#mReports').css("display", "block");
		$('#mPayments').css("display", "block");
		$('#mAdmin').css("display", "block");
	}
}

function Logout() {

	sessionStorage.removeItem('Email');
	sessionStorage.removeItem('Role');
	$('#mRegistration').css('display', 'block');
	$('#mLogin').css('display', 'block');
	$('#mLogout').css('display', 'none');
	$('#mWelcome').css('display', 'none');
	$('#mRoles').css("display", "none");
	$('#mJobs').css("display", "none");
	$('#mProdType').css("display", "none");
	$('#mProd').css("display", "none");
	$('#mEmpDetails').css("display", "none");
	$('#mOffers').css("display", "none");
	$('#mInventory').css("display", "none");
	$('#mEngAssignment').css("display", "none");
	$('#mInvoice').css("display", "none");
	$('#mReports').css("display", "none");
	$('#mPayments').css("display", "none");
	$('#mAdmin').css("display", "none");
	
	window.location.href = "/index.html";
}

function onChange() {

	var proTypeId = $('#dropdown').val();
	$('#proTypeId').val(proTypeId);

	var userId = $('#ddlEngineer').val();
	$('#userId').val(userId);

	var jobId = $('#ddlJobs').val();
	$('#jobId').val(jobId);
	
	var userId = $('#ddlUsers').val();		
	$('#userId').val(userId);		
					
	var rolId = $('#ddlRoles').val();		
	$('#rolId').val(rolId);

}

function FillEngineerDropDown() {
	$.ajax({
		type : "GET",
		url : "/GetEngineerList",
		success : function(data) {
			debugger;
			$('#ddlEngineer').kendoDropDownList({
				dataSource : data,
				dataTextField : "fullName",
				dataValueField : "userId",
				change : onChange,
				optionLabel : "Select Engineer"
			});

		},
		error : function(e) {
			debugger;
			console.log(e);
		}

	});

}

function FillJobsDropDown() {
	$.ajax({
		type : "GET",
		url : "/GetJobsList",
		success : function(data) {
			debugger;
			$('#ddlJobs').kendoDropDownList({
				dataSource : data,
				dataTextField : "vin",
				dataValueField : "jobId",
				change : onChange,
				optionLabel : "Select Vehicle number"
			});

		},
		error : function(e) {
			debugger;
			console.log(e);
		}

	});

}

function GetParameterValues(param) {
	var url = window.location.href.slice(window.location.href.indexOf('?') + 1)
			.split('&');
	for (var i = 0; i < url.length; i++) {
		var urlparam = url[i].split('=');
		if (urlparam[0] == param) {
			return urlparam[1];
		}
	}
}
function profilepage() {
	var userEmail = GetParameterValues("userEmail");
	$.ajax({
		type : "GET",
		url : "/GetEmail?userEmail=" + userEmail,
		success : function(data) {
			debugger;
			$('#userFirstName').val(data.userFirstName);
			$('#userLastName').val(data.userLastName);
			$('#userEmail').val(data.userEmail);
			$('#userMob').val(data.userMob);
			$('#fullName').text(data.userFirstName + " " + data.userLastName);
			$('#userId').text(data.userEmail);
			$('#userDob').val(data.userDob);
			$('#userContact').val(data.userContact);
			$('#userAddress').val(data.userAddress);
			$('#userPan').val(data.userPan);
			$('#userAdhar').val(data.userAdhar);
			$('#ddlExp').val(data.userWorkExp);
			$('#userJoiningDate').val(data.userJoiningDate);
			$('#userResignDate').val(data.userResignDate);
			$('#userImagePath').attr('src',data.userImagePath);
			$('#updateDate').text(data.updateDate);
		},
		error : function(e) {
			debugger
			console.log(e);
		}

	});
};

function getFormattedDate(value) {

	var date = new Date(value);
	var year = date.getFullYear();
	var month = (1 + date.getMonth()).toString();
	month = month.length > 1 ? month : '0' + month;

	var day = date.getDate().toString();
	day = day.length > 1 ? day : '0' + day;

	return (day + '/' + month + '/' + year);

}

function ConvertDates()
{
	debugger;
	var serDate=$('#serDate').text();
	$('#serDate').text(getFormattedDate(serDate));
	var delDate=$('#delDate').text();
	$('#delDate').text(getFormattedDate(delDate));
	
	
}


function previewFile() {
debugger;

var controlId="#userImagePath";
    var file = $('#upload')[0].files[0];

    var fileSize = file.size / 1024 / 1024;
    var ext = file.name.split('.').pop();

    if (ext.toLocaleLowerCase() == "jpg" || ext.toLocaleLowerCase() == "jpeg" || ext.toLocaleLowerCase() == "jpe" || ext.toLocaleLowerCase() == "gif") {
        if (fileSize > 8) {
            alert("Uploaded file size cannot exceed 8 MB.");            
            return;
        }
        else {
            var reader = new FileReader();
            if (file) {
            	reader.onload = function (e) {
                    $(controlId).attr("src", e.target.result);
                }
                reader.readAsDataURL(file);
                

            } else {
                preview.src = "";
            }
        }
    }
    else {
        alert("Only JPG and GIF images are accepted (*.jpg; *.jpeg; *.jpe; *.gif).");
              return;
    }

}

function SelectExp()
{
	$('#userWorkExp').val($('#ddlExp').val());	
}


Date.prototype.yyyymmdd = function() {
	  var yyyy = this.getFullYear().toString();
	  var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based
	  var dd  = this.getDate().toString();
	  return (mm[1]?mm:"0"+mm[0]) + "/" + (dd[1]?dd:"0"+dd[0])+ "/" + yyyy ;   // padding
	};

	
function GetInvoiceData()
{
	$.ajax({
		type:"GET",
		url:"/GetInvoiceData",
		success:function(data)
		{
			$('#invNo').val(data);

			var date = new Date();
				
			$('#invDate').val(date.yyyymmdd());
			$('#serDate').val(date.yyyymmdd());
			$('#invType').val('CASH-MEMO');
		},
		error:function()
		{
			
		}
		
	});
}

function FillProductDropDown()
{debugger;

	$.ajax({
		type:"GET",
		url:"/GetProductList",
		success:function(data)
		{
			$('#divProduct').kendoDropDownList({
				dataSource : data,
				dataTextField : "proName",
				dataValueField : "proId",
				change : selectProduct,
				optionLabel : "Select Product"
			});
	     },
		error:function(e)
		{
		console.log(e);	
		}
		
	});	
}

function selectProduct()
{
	var proId=$('#divProduct').val();
	$.ajax({
		type:"GET",
		url:"/GetSelectedProduct?proId="+proId,
		success:function(data)
		{
			$('#proRemark').val(data.proDes);
			$('#proManDate').val(data.proManDate);
			$('#proRate').val(data.proSaleRate);
			$('#proQty').val(1);
			$('#proDis').val(0);
			$('#proTotal').val(($('#proQty').val()*$('#proRate').val())-$('#proDis').val());
	        $('#proCGST').val($('#proTotal').val()/100*6);
	        $('#proSGST').val($('#proTotal').val()/100*6);
		    $('#proGrossTotal').val(parseInt($('#proTotal').val())+parseInt($('#proCGST').val())+  parseInt($('#proSGST').val()));
		},
		error:function(e)
		{
		console.log(e);	
		}
		
	});		

}

var listProd=[];
var sno;
function AddProduct()
{
	debugger;
	if(sno==undefined|| sno==null)
		{
		sno=0;
		}
	sno=sno+1;
	var prod={
	sno:sno,
	proId:$('#divProduct').val(),
	proName:$("#divProduct").data("kendoDropDownList").text(),
	remark:$('#proRemark').val(),
	manDate:$('#proManDate').val(),
	qty:$('#proQty').val(),
	rate:$('#proRate').val(),
	dis:$('#proDis').val(),
	total:$('#proTotal').val(),
	cgst:$('#proCGST').val(),
	sgst:$('#proSGST').val(),
	grossTotal:$('#proGrossTotal').val(),
	invNo:$('#invNo').val()		
	};
	
	listProd.push(prod);
	
	FillProdGrid(listProd);
		
	debugger;
	var grossTotal=$('#proGrossTotal').val();
	var oldTotal=$('#totalAmount').text();
	$('#totalAmount').text(parseInt(oldTotal)+parseInt(grossTotal));
	ClearControls();
}

function ClearControls()
{
	$("#divProduct").data("kendoDropDownList").text('Select Product');	
	$('#proRemark').val('');
	$('#proManDate').val('');
	$('#proQty').val('');
	$('#proRate').val('');
	$('#proDis').val('');
	$('#proTotal').val('');
	$('#proCGST').val('');
	$('#proSGST').val('');
	$('#proGrossTotal').val('');
	$('#proDisPer').val('');
	$('#proOldQty').val('');
	$('#proNewQty').val('');
	$('#inStock').val('');
	$('#proId').val('');
}

function FillProdGrid(list)
{
	 $("#grid").kendoGrid({
         dataSource: list,
         width: '971px',         
         pageable: false,
         columns:[{
             field: "sno",
             title: "Sno",
             width: 40,
             },               
             {
            	 field: "proName",
                 title: "Product Name",
                 width: 140,
             },
             {
            	 field: "remark",
                 title: "Remark",
                 width: 80,
             },
             {
            	 field: "manDate",
                 title: "Man.Date",
                 width: 90,
             },
             {
            	 field: "qty",
                 title: "Quantity",
                 width: 70,
             },
             {
            	 field: "rate",
                 title: "Rate",
                 width: 80,
             },
             {
            	 field: "dis",
                 title: "Dis.",
                 width: 70,
             },
             {
            	 field: "total",
                 title: "Total",
                 width: 70,
             },
             {
            	 field: "cgst",
                 title: "CGST",
                 width: 70,
             },
             {
            	 field: "sgst",
                 title: "SGST",
                 width: 70,
             },
             {
            	 field: "grossTotal",
                 title: "GrossTotal",
                 width: 70,
             },
             {
                 field: "sno",
                 template: "<input name='#=sno #' value='Delete' id='#=sno #' onclick='DeleteProd(#= sno #)' class='css-checkbox' type='button'></input>",
                 title: "Delete",
                 width: '100px',
                 attributes: { style: "text-align:center;" },
             },
             ],
     });
}

function QtyChange()
{
	debugger;
	var qty=$('#proQty').val();

	var oldqty=$('#proOldQty').val();
	var newqty=$('#proNewQty').val();
	
	$('#proTotal').val((qty*$('#proRate').val())-$('#proDis').val());
    $('#proCGST').val($('#proTotal').val()/100*6);
    $('#proSGST').val($('#proTotal').val()/100*6);
    $('#proGrossTotal').val(parseInt($('#proTotal').val())+parseInt($('#proCGST').val())+  parseInt($('#proSGST').val()));


    $('#inStock').val((parseInt(oldqty) + parseInt(newqty)));
}

function DisChange()
{

	debugger;
	var dis=$('#proDisPer').val();
	
	$('#proDis').val($('#proTotal').val()*dis/100);
	
	$('#proTotal').val($('#proTotal').val()-$('#proDis').val());
	   
	$('#proCGST').val($('#proTotal').val()/100*6);
    $('#proSGST').val($('#proTotal').val()/100*6);
    $('#proGrossTotal').val(parseInt($('#proTotal').val())+parseInt($('#proCGST').val())+  parseInt($('#proSGST').val()));

}



function AddInvoice() {
	debugger;
	
	var inv = {
			invNo :$('#invNo').val(),
		    invDate :'2018-02-02',
		    invType :$('#invType').val(),
			serDate :'2018-02-02',
			custName :$('#custName').val(),
			custAddress :$('#custAddress').val(),
			custMob :$('#custMob').val(),
			custVehNo :$('#custVehNo').val(),
			custVehType :$('#custVehType').val(),
			custVehModel :$('#custVehModel').val(),
			remark :$('#remark').val(),
			nextSerDate :'2018-02-02',
			totalBillAmount :$('#totalAmount').text(),	
			disAmount :"0",
			grossTotal :$('#totalAmount').text(),		
		    listProducts :listProd
	};

	$.ajax({
		type : "POST",
		url : "/AddInvoice",
		data : JSON.stringify(inv),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {			
			window.location.href="/Invoice.html";
		},
		error : function(e) {
			debugger;
			console.log(e);
		}
	});
}







function ViewInvoice()
{
	debugger;
GetModelReady("#ViewInvoice", "#invoiceModel","View Invoice Details","ViewInvoiceProductDetails.html","750px","500px");	
}

function ControllerCall(invNo)
{debugger;

$('#invoiceModel').data("kendoWindow").center().open();
$.ajax({
	type:"GET",
	url:"/GetInvoiceProDetails?invNo="+invNo,
	success:function(data)
	{
		debugger;
		var table="<table id='invProdTable' border='1'><thead><tr><th>" +
				"<b style='color: black'>Inv.No</b></th> "+
				"<th><b style='color: black'>Product Name</b></th> "+
				"<th><b style='color: black'>Manuf.Date</b></th> "+
				"<th><b style='color: black'>Quantity</b></th> "+
				"<th><b style='color: black'>Rate</b></th> "+ 
				"<th><b style='color: black'>Discount</b></th>"+
				"<th><b style='color: black'>Total</b></th>"+
				"<th><b style='color: black'>CGST</b></th>"+
				"<th><b style='color: black'>SGST</b></th>"+
				"<th><b style='color: black'>Gross Total</b></th>"+
				"</tr></thead><tbody>";
		for(var i=0;i<data.length;i++){
			table=table+"<tr><td>"+data[i].invNo+"</td>"+
								"<td>"+data[i].proName+"</td>"+
								"<td>"+data[i].manDate+"</td>"+
								"<td>"+data[i].qty+"</td>"+
								"<td>"+data[i].rate+"</td>"+
								"<td>"+data[i].dis+"</td>"+
								"<td>"+data[i].total+"</td>"+
								"<td>"+data[i].cgst+"</td>"+
								"<td>"+data[i].sgst+"</td>"+
								"<td>"+data[i].grossTotal+"</td>"+
							    "</tr>";
		}
		table=table+"</tbody></table>";
		
		$('#invProdTable').replaceWith(table);
	},
	error:function(e)
	{
		debugger;
		console.log(e);
	}
	
});
	
}


function DeleteProd(rowId)
{debugger;
var amount=0;
$.each(listProd, function(i, el){
    if (this.sno == rowId){
    	amount=this.grossTotal;
        listProd.splice(i, 1);
    }
});

FillProdGrid(listProd);

var oldTotal=$('#totalAmount').text();
$('#totalAmount').text(parseInt(oldTotal)-parseInt(amount));
ClearControls();

}

function SearchInvoice()
{
	debugger;
	var startDate=$('#invStartDate').val();
	var endDate=$('#invEndDate').val();
    $.ajax({
	type:"GET",
	url:"/GetInvoice?startDate="+startDate+"&endDate="+endDate+"",
    success:function(data)
	{
    	debugger;
    	var str="<table id='invTable' border='1'><thead><tr> "+
									"<th><b style='color: black'>Inv.No </b></th> "+
									"<th><b style='color: black'>Inv.Date </b></th> "+
									"<th><b style='color: black'>Customer Name </b></th> "+
									"<th><b style='color: black'>Cust.Mob</b></th> "+
									"<th><b style='color: black'>Veh.No </b></th> " +
									"<th><b style='color: black'> Veh.Model </b></th> "+
									"<th><b style='color: black'>Total Bill Amount </b></th>"+
									"<th><b style='color: black'>Action </b></th> "+
								    "</tr></thead>";
								    

    	for(var i=0;i<data.length;i++)
    		{
    		
    		str= str + "<tr> "+
			"<td id='invNo'>"+data[i].invNo+"</td>"+
			"<td>"+data[i].invDate+"</td>"+
			"<td>"+data[i].custName+"</td>"+
			"<td>"+data[i].custMob+"</td>"+
			"<td>"+data[i].custVehNo+"</td>"+
			"<td>"+data[i].custVehModel+"</td>"+
			"<td>₹"+data[i].totalBillAmount+"</td>"+
			"<td><input type='button' value='View Invoice' onclick=\'ControllerCall("+data[i].invNo +");\'></td>"+
			"</tr>";
		    
    		}
    	    str=str+"</table>";
				
				$('#invTable').replaceWith(str);
				ViewInvoice();
    	
	},
	error:function(e)
	{
		debugger;
	}
	
});	
}


function FillProductDropDownInventory()
{

	$.ajax({
		type:"GET",
		url:"/GetProductList",
		success:function(data)
		{
			$('#divProduct').kendoDropDownList({
				dataSource : data,
				dataTextField : "proName",
				dataValueField : "proId",
				change : selectProd,
				optionLabel : "Select Product"
			});
	     },
		error:function(e)
		{
		console.log(e);	
		}
		
	});	
}

function selectProd()
{
	var proId=$('#divProduct').val();
	$.ajax({
		type:"GET",
		url:"/GetSelectedProd?proId="+proId,
		success:function(data)
		{
			$('#proOldQty').val(data.inStock);
			$('#proNewQty').val(data.proNewQty);
			$('#inStock').val(data.inStock);
		
		},
		error:function(e)
		{
		console.log(e);	
		}
		
	});		

}


var listInventory=[];

function AddInventory()
{
	debugger;
	var invtory={	
	proId:$('#divProduct').val(),
	proName:$("#divProduct").data("kendoDropDownList").text(),
	proOldQty:parseInt($('#proOldQty').val()),
	proNewQty:parseInt($('#proNewQty').val()),
	inStock:parseInt($('#inStock').val()),
		};
	
	listInventory.push(invtory);
	
	FillProdGridInv(listInventory);
		
	
	ClearControls();
}



function SubmitInventory() {
	
	debugger;
	$.ajax({
		type : "POST",
		url : "/AddInventory",
		data : JSON.stringify(listInventory),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {	
			debugger;
			window.location.href="/Inventory.html";
		},
		error : function(e) {
			debugger;
			console.log(e);
		}
	});
}


function FillAllUsersDropDown() {
	$.ajax({
		type : "GET",
		url : "/ViewAllUsers",
		success : function(data) {
			debugger;
			$('#ddlUsers').kendoDropDownList({
				dataSource : data,
				dataTextField : "fullName",
				dataValueField : "userId",
				change : onChange,
				optionLabel : "Select Employee"
			});

		},
		error : function(e) {
			debugger;
			console.log(e);
		}

	});

}


function FillRolesDropDown() {
	$.ajax({
		type : "GET",
		url : "/GetRoles",
		success : function(data) {
			debugger;
			$('#ddlRoles').kendoDropDownList({
				dataSource : data,
				dataTextField : "rolName",
				dataValueField : "rolId",
				change : onChange,
				optionLabel : "Select Role"
			});

		},
		error : function(e) {
			debugger;
			console.log(e);
		}

	});

}

function FillProdGridInv(list)
{
	 $("#inventorygrid").kendoGrid({
         dataSource: list,
         width: '971px',         
         pageable: false,
         columns:[              
             {
            	 field: "proName",
                 title: "Product Name",
                 width: 140,
             },
             {
            	 field: "proOldQty",
                 title: "Prod Old Qty",
                 width: 80,
             },
             {
            	 field: "proNewQty",
                 title: "Prod New Qty",
                 width: 90,
             },
             {
            	 field: "inStock",
                 title: "Prod In Stock",
                 width: 70,
             }],
     });
}

function printpage() {
    //Get the print button and put it into a variable
    var printButton = document.getElementById("printpagebutton");
    //Set the print button visibility to 'hidden' 
    printButton.style.visibility = 'hidden';
    //Print the page content
    window.print()
    printButton.style.visibility = 'visible';
}


function SearchInvoiceByInvNo()
{
	debugger;
	var invNo=$('#invStartDate').val();
	
	$.ajax({
	type:"GET",
	url:"/GetInvoiceByInvNo?invNo="+invNo,
    
	success:function(data)
	{
    	debugger;
    	var str="<table id='invTable' border='1'><thead><tr> "+
									"<th><b style='color: black'>Inv.No </b></th> "+
									"<th><b style='color: black'>Inv.Date </b></th> "+
									"<th><b style='color: black'>Customer Name </b></th> "+
									"<th><b style='color: black'>Cust.Mob</b></th> "+
									"<th><b style='color: black'>Veh.No </b></th> " +
									"<th><b style='color: black'> Veh.Model </b></th> "+
									"<th><b style='color: black'>Total Bill Amount </b></th>"+
									"<th><b style='color: black'>Action </b></th> "+
								    "</tr></thead>";
								    

    	for(var i=0;i<data.length;i++)
    		{
    		
    		str= str + "<tr> "+
			"<td id='invNo'>"+data[i].invNo+"</td>"+
			"<td>"+data[i].invDate+"</td>"+
			"<td>"+data[i].custName+"</td>"+
			"<td>"+data[i].custMob+"</td>"+
			"<td>"+data[i].custVehNo+"</td>"+
			"<td>"+data[i].custVehModel+"</td>"+
			"<td>₹"+data[i].totalBillAmount+"</td>"+
			"<td><input type='button' value='View Invoice' onclick=\'ControllerCall("+data[i].invNo +");\'></td>"+
			"</tr>";
		    
    		}
    	    str=str+"</table>";
				
				$('#invTable').replaceWith(str);
				ViewInvoice();
    	
	},
	error:function(e)
	{
		debugger;
	}
	
});	
}