<script>
	var repo = window.repo ? window.repo : {};
	repo.serviceUrl = "http://rest-service-repodepo.192.168.99.100.nip.io/";
	
	repo.objectDefinition = {};
	
	repo.objectDefinition["application"] = {
			"id":{datatype: "String", label: "ID", hidden:true},
			"appName":{datatype: "String", label: "Application Name", hidden:true},
			"appDescrption":{datatype: "String", label: "Description", hidden:true}		
	};

	repo.objectDefinition["table"] = {
			"id":{datatype: "String", label: "ID", hidden:true},
			"tableName":{datatype: "String", label: "Table Name", hidden:true},
			"tableDescrption":{datatype: "String", label: "Description", hidden:true}		
	};	
	
/* 	repo.objectDefinition["Item"] = {
			"id":{datatype: "String", label: "ID", hidden:true},
			"ItemNumber":{datatype: "String", label: "Item Number", hidden:true},
			"ItemDesc":{datatype: "String", label: "Item Description", hidden:true}		
	};	
	
	repo.objectDefinition["Product"] = {
			"id":{datatype: "String", label: "ID", hidden:true},
			"ProductName":{datatype: "String", label: "Name of the product", hidden:true},
			"ProductID":{datatype: "String", label: "ID of the product", hidden:true},
			"ProductType":{datatype: "object", label: "Type of the product", hidden:true},
			"ProductCategory":{datatype: "array", label: "Category of the product", hidden:true},
			"ExpireDate":{datatype: "date", label: "Date of expire", hidden:true},
			"Availability":{datatype: "bool", label: "Available Status", hidden:true}
	};
	
   	repo.objectDefinition["Employee"] = {
			"id":{datatype: "String", label: "ID", hidden:true},
			"EmployeeID":{datatype: "int", label: "ID of the Employee", hidden:true},
			"First_Name":{datatype: "String", label: "First Name", hidden:true}		
	};	 
	 
	repo.objectDefinition["Departments"] = {
			"id":{datatype: "String", label: "ID", hidden:true},
			"DepartmentName":{datatype: "String", label: "Name of the department ", hidden:true},
			"HOD":{datatype: "String", label: "Head of the department", hidden:true}		
	};
	
	repo.objectDefinition["Subjects"] = {
			"id":{datatype: "String", label: "ID", hidden:true},
			"SubjectName":{datatype: "String", label: "Name of the Subject", hidden:true},
			"SubjectCode":{datatype: "String", label: "Code given to the Subject", hidden:true}		
	};
	
	repo.objectDefinition["Relationship"] = {
			"primaryTable":{datatype: "String", label: "Primary Table", hidden:true},
			"secondaryTable":{datatype: "String", label: "Secondary Table", hidden:true},
			"minimumMultiplicity":{datatype: "String", label: "Minimum Multiplicity", hidden:true},
			"maximumMultiplicity":{datatype: "String", label: "Maximum Multiplicity", hidden:true}
	};
	
	repo.objectDefinition["Students"] = {
			"id":{datatype: "String", label: "ID", hidden:true},
			"FirstName":{datatype: "String", label: "Name of the Student", hidden:true},
			"LastName":{datatype: "String", label: "Lastname of the student", hidden:true},
			"Address":{datatype: "textarea", label: "Address of the Student", hidden:true},
			"Gender":{datatype: "String", label: "Gender of the Student", hidden:true},
			"DateOfBirth":{datatype: "date", label: "DOB of the Student", hidden:true}
			
				
	}; */
	
</script>

 <script>

    var keycloak = Keycloak('keycloak.json');

    var loadFailure = function () {
       alert('<b>Failed to load data.  Check console log</b>');
    };

    var loadData = function () {
     	var head = document.getElementsByTagName('head')[0];
    	var js = document.createElement("script");
    	js.type = "text/javascript";
    	js.src = "helloworld/helloworld.nocache.js";
    	head.appendChild(js); 
    	//document.write("<"+"script src=helloworld/helloworld.nocache.js><"+"/script>");    	
    };
    
    var reloadData = function () {
        keycloak.updateToken(5)
        .success(loadData)
        .error(loadFailure);
    }

    keycloak.init({ onLoad: 'login-required' })
        .success(reloadData)
        .error(loadFailure);
    
    setTimeout(keycloak.updateToken(5), 4000);

</script> 


<html>
 <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Repo Depo - User</title>
    <meta name="viewport" content="initial-scale=1, width=device-width, user-scalable=no, minimum-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <!--CSS for loading message at application Startup-->
    <style type="text/css">
        html, body { overflow:hidden }
        #loadingWrapper {
            position: absolute;
            top: 40%;
            width: 100%;
            text-align: center;
            z-index: 900001;
        }
        #loading {
            margin: 0 auto;
            border: 1px solid #ccc;
            width: 160px;
            padding: 2px;
            text-align: left;
        }

        #loading a {
            color: #225588;
        }

        #loading .loadingIndicator {
            background: white;
            font: bold 13px tahoma, arial, helvetica;
            padding: 10px;
            margin: 0;
            height: auto;
            color: #444;
        }

        #loadingMsg {
            font: normal 10px arial, tahoma, sans-serif;
        }
    </style>
    <link rel="stylesheet" href="RepoUser.css?isc_version=12.0p_2018-06-30.css">
    
</head>
<body>
<iframe id="__gwt_historyFrame" style="width:0;height:0;border:0"></iframe>

<!--add loading indicator while the app is being loaded-->
<div id="loadingWrapper">
<div id="loading">
    <div class="loadingIndicator">
        <!--<img src="images/pieces/48/cube_green.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/>SmartGWT<br/>-->
        <img src="images/loading.gif" width="16" height="16" style="margin-right:8px;float:left;vertical-align:top;"/>Repo Depo<br/>
        <span id="loadingMsg">Loading styles and images...</span></div>
</div>
</div>

<!-- IMPORTANT : You must set the variable isomorphicDir to [MODULE_NAME]/sc/ so that the Smart GWT
     resource are correctly resolved -->
<script type="text/javascript">
var isomorphicDir = "helloworld/sc/";
document.getElementById('loadingMsg').innerHTML = 'Loading Core API...';
</script>
<!--include the SC Core API-->
<script type="text/javascript" src='helloworld/sc/modules/ISC_Core.js?isc_version=12.0p_2018-06-30.js'></script>

<!--include SmartClient -->
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading UI Components...';</script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_Foundation.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_Containers.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_Grids.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_Forms.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_RichTextEditor.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading Data API...';</script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_DataBinding.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_Calendar.js?isc_version=12.0p_2018-06-30.js'></script>
<script type="text/javascript" src='helloworld/sc/modules/ISC_Drawing.js?isc_version=12.0p_2018-06-30.js'></script>


<script type="text/javascript">
function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

// Determine what skin file to load
var currentSkin = readCookie('skin_name_2_4');
if (currentSkin == null) currentSkin = isc.Browser.defaultSkin;
</script>

<!--load skin-->
<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading skin...';</script>

<script type="text/javascript">
document.write("<"+"script src=helloworld/sc/skins/" + currentSkin + "/load_skin.js?isc_version=12.0p_2018-06-30.js><"+"/script>");
</script>

<script type="text/javascript">document.getElementById('loadingMsg').innerHTML = 'Loading Application<br>Please wait...';</script>

<script type="text/javascript">document.getElementById('loading').remove();</script>

<!--include the application JS
<script type="text/javascript" src="helloworld/helloworld.nocache.js"></script>-->

</body>
</html>