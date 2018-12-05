var myApp = angular.module("myApp", []);

myApp.controller("rolesController", function($scope, $http) {

	$http.get("/GetRoles").then(function(response) {
		$scope.roleList = response.data;
	});
});

$(document).ready(function() {
	BindHeaderFooter();	
	FillProductTypeDropDown();
});

function GetModelReady(ButtonId, modelID) {

	debugger;
	var myWindow = $(modelID);
	var button = $(ButtonId);

	AlignModelCenter(ButtonId, modelID);

	myWindow.kendoWindow({
		draggable : false,
		title : "User Login",
		minWidth : "300px",
		maxWidth : "500px",
		maxHeight : "400px",
		visible : false,
		resizable : false,
		modal : true,
		content : "LoginPage.html",

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
				optionLabel: "Select Product Type"
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
			GetModelReady("#btnLogin", "#loginModel");
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

	window.location.href = "/index.html";
}

function onChange() {

	var proTypeId=$('#dropdown').val();
	$('#proTypeId').val(proTypeId);
}
