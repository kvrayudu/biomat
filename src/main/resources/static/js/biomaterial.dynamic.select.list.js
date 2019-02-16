$(document).ready(function () {
	
	$("#selectedBioVariableId" ).on( "focus", function() {
	    console.log( "selectedBioVariableId got focus. now fetching selected Material" );
	    
	    $("#selectedBioVariableId").empty();
	    $("#selectedDependentBioVariableId").empty();
	    $("#selectedBioFormulaId").empty();
	    jQuery('#materialComposition').empty();
	    
	    var materialId = $("#selectedBioMaterialId").val();
	    
		if(materialId != '' ){
			console.log( "Got MaterialID " +  materialId + " Now fetching variables for the material");
			$.get( "getVariablesForBiMaterial?materialId="+materialId, function( response ) {
				$('#selectedBioVariableId').empty()
					var jsonValue = JSON.parse(response);
					var variableOptionsString="";
					for( var i=0;i<jsonValue.data.length;i++){
						variableOptionsString += "<OPTION value = "+jsonValue.data[i].id+">"+jsonValue.data[i].name+"</OPTION>";
						var o = new Option( jsonValue.data[i].name,jsonValue.data[i].id);
						$(o).html(jsonValue.data[i].name);
						$("#selectedBioVariableId").append(o);	
					}
					console.log( variableOptionsString );
			})
		}
		else{
			console.log( "Must select a material before selecting a variable.");
			$("#selectedBioMaterialId").focus();
			$.alert({
			    title: 'Alert!',
			    content: 'Must select a material before selecting a variable.!',
			});
		}
	});
	
	
	$("#selectedDependentBioVariableId" ).on( "focus", function() {
	    var materialId = $("#selectedBioMaterialId").val();
	    var variableId = $("#selectedBioVariableId").val();
	    console.log( "selectedBioDependentVariableId got focus selected materialId:variabledId " +  materialId + ":" + variableId );

	    $("#selectedDependentBioVariableId").empty();
	    $("#selectedBioFormulaId").empty();
	    jQuery('#materialComposition').empty();
	    
	    if(materialId == '' ){
			console.log( "Must select a material before selecting a variable.");
			$("#selectedBioMaterialId").focus();
			$.alert({
				title: 'Alert!',
				content: 'Must select a material and variable before selecting a dependent variable.!',
			});
			return;
	    }
	    
	    if(variableId == '' || variableId == null){
			console.log( "Must select a Variable efore selecting a Dependent Variable.");
			$("#selectedBioVariableId").focus();
			$.alert({
				title: 'Alert!',
				content: 'Must select a Variable before selecting a Dependent Variable.!',
			});
			return;
	    }
	    
	    
		
		$.get("getDependentVariablesForBiMaterial?materialId="+materialId + "&variableId=" +variableId, function( response ) {
			$('#selectedDependentBioVariableId').empty()
				var jsonValue = JSON.parse(response);
				var variableOptionsString="";
				for( var i=0;i<jsonValue.data.length;i++){
					variableOptionsString += "<OPTION value = "+jsonValue.data[i].id+">"+jsonValue.data[i].name+"</OPTION>";
					var o = new Option( jsonValue.data[i].name,jsonValue.data[i].id);
					$(o).html(jsonValue.data[i].name);
					$("#selectedDependentBioVariableId").append(o);	
				}
				console.log( variableOptionsString );
		})
	    
	    
	});
	
	
	$("#selectedBioFormulaId" ).on( "focus", function() {
	    var materialId = $("#selectedBioMaterialId").val();
	    var variableId = $("#selectedBioVariableId").val();
	    var dependentVariableId = $("#selectedDependentBioVariableId").val();
	    jQuery('#materialComposition').empty();
	    console.log( "selectedBioFormulaId   got focus selectedBioDependentVariableId selected materialId:variabledId:dependentVariableId " +  materialId + ":" + variableId  + ":"+ dependentVariableId);
	    $("#selectedBioFormulaId").empty();

	    if(materialId == '' ){
			console.log( "Must select a material before selecting a variable.");
			$("#selectedBioMaterialId").focus();
			$.alert({
				title: 'Alert!',
				content: 'Must select a material, variable  and dependent Variable before selecting a Formula.!',
			});
			return;
	    }
	    
	    if(variableId == '' || variableId == null){
			console.log( "Must select a Variable before selecting a Dependent Variable.");
			$("#selectedBioVariableId").focus();
			$.alert({
				title: 'Alert!',
				content: 'Must select a material, variable  and dependent Variable before selecting a Formula.!',
			});
			return;
	    }

	    if(dependentVariableId == '' || dependentVariableId == null){
			console.log( "Must select a material, variable  and dependent Variable before selecting a Formula.");
			$("#selectedDependentBioVariableId").focus();
			$.alert({
				title: 'Alert!',
				content: 'Must select a material, variable  and dependent Variable before selecting a Formula.!',
			});
			return;
	    }

	    
	
		$.get("getBioFormula?materialId="+materialId + "&variableId=" +variableId+ "&dependentVariableId=" +dependentVariableId, function( response ) {
			$('#selectedBioFormulaId').empty()
				var jsonValue = JSON.parse(response);
				var optionsString="";
				for( var i=0;i<jsonValue.data.length;i++){
					optionsString += "<OPTION value = "+jsonValue.data[i].id+">"+jsonValue.data[i].formulaAndName+"</OPTION>";
					var o = new Option( jsonValue.data[i].formulaAndName,jsonValue.data[i].id);
					$(o).html(jsonValue.data[i].formulaAndName);
					$("#selectedBioFormulaId").append(o);	
				}
				console.log( optionsString );
		})
	    
	
	});

/* Material Composition START**/

	
	$("#add-material-composition" ).on( "click", function() {
		var materialId = $("#selectedBioMaterialId").val();
		var formulaId = $("#selectedBioFormulaId").val();
		jQuery('#materialComposition').empty();
		$.get("getBioMatrialNutrients?materialId="+materialId +"&formulaId="+formulaId, function( response ) {
			
			var jsonValue = JSON.parse(response);
			
				if(jsonValue.data.length==0){
					$.alert({
						title: 'Alert!',
						content: 'No user input is needed. Please proceed to Chart Formula.!',
					});
					return;
				}
			
				var inptString= "		<div class='row' >\n" + 
				"			<div class='col-sm-1'>&nbsp;</div>\n" + 
				"			<div class='col-sm-2 text-info'><b>Material Composition </b></div>\n" + 
				"			<div class='col-sm-2 text-info'><b>Unit</b></div>\n" + 
				"			<div class='col-sm-2 text-info'><b>USDA Value</b> </div>\n" + 
				"			<div class='col-sm-2 text-info'><b>Change Value(Optional)</b></div>\n" + 
				"		</div>\n" + 
				"";
				for( var i=0;i<jsonValue.data.length;i++){
					//inptString+=jsonValue.data[i].nutrientName + "&nbsp;&nbsp;" +jsonValue.data[i].nutrientValue
					inptString+="<div class='form-group row'>\n" + 
					"			<input  type='hidden'   value = '" + jsonValue.data[i].bioMaterialId	+"' 	id='bioMaterialNutrientModelList["+i+"].bioMaterialId' name='bioMaterialNutrientModelList["+i+"].bioMaterialId'/>\n" + 
					"			<input type='hidden'  value = '" + jsonValue.data[i].bioNutrientId 	+"' 	id='bioMaterialNutrientModelList["+i+"].bioNutrientId' name='bioMaterialNutrientModelList["+i+"].bioNutrientId'/>			\n" + 
					"			<input  type='hidden'  value = '" + jsonValue.data[i].nutrientName	+"' 	id='bioMaterialNutrientModelList["+i+"].nutrientName' name='bioMaterialNutrientModelList["+i+"].nutrientName'/>			\n" + 
					"			<input  type='hidden' value = '" + jsonValue.data[i].nutrientUnit	+"' 	id='bioMaterialNutrientModelList["+i+"].nutrientUnit' name='bioMaterialNutrientModelList["+i+"].nutrientUnit'/>			\n" + 
					"			<input  type='hidden' value = '" + jsonValue.data[i].nutrientValue	+"' 	id='bioMaterialNutrientModelList["+i+"].nutrientValue' name='bioMaterialNutrientModelList["+i+"].nutrientValue'/>			\n" +
					"			<input  type='hidden' value = '" + jsonValue.data[i].nutrientSymbol	+"' 	id='bioMaterialNutrientModelList["+i+"].nutrientSymbol' name='bioMaterialNutrientModelList["+i+"].nutrientSymbol'/>			\n" + 
					"\n" + 
					"			<div class='col-sm-1'>&nbsp;</div>\n" + 
					"			<div class='col-sm-2 text-info'>"+jsonValue.data[i].nutrientName+"</div>  \n" + 
					"			<div class='col-sm-2 text-info'>"+jsonValue.data[i].nutrientUnit+"</div>\n" + 
					"			<div class='col-sm-2 text-info'>"+jsonValue.data[i].nutrientValue+"</div>\n" + 
					"			<div class='col-sm-2'>\n" + 
					"				<input class='form-control' id='bioMaterialNutrientModelList["+i+"].userValue' name='bioMaterialNutrientModelList["+i+"].userValue' value ="+jsonValue.data[i].nutrientValue+"  />\n" + 
					"			</div>\n" + 
					"			\n" + 
					"		</div>";
					
				}
				//input = jQuery(inptString);
				jQuery('#materialComposition').append(inptString);
				console.log( inptString );
		})
	    

    	
    	return;
	});
	
	$("#graph-bio-material" ).on( "click", function() {
	//$('#exampleModal').on('shown.bs.modal',function() {
		var formData = $("#graphForm").serialize();
		var labelText = $("#selectedBioFormulaId").text()
		var dependentVariableId = $("#selectedDependentBioVariableId").val();
		//alert(dependentVariableId);
	//	alert(labelText )
		console.log( formData );
		$.post("getCalculatedDataPoints", formData,function( response ){
			var jsonValue = JSON.parse(response);
			var dataArray=jsonValue.data.dataPointsY;
			console.log( response );
			console.log( "DataArrayY " + dataArray );
			var xAxisLabelsArray		= jsonValue.data.dataPointsX;
			console.log( "DataArrayX " + xAxisLabelsArray );
			
			var popCanvas = $("#popChart");
			var popCanvas = document.getElementById("popChart");
			var popCanvas = document.getElementById("popChart").getContext("2d");
			var barChart = new Chart(popCanvas, {
				  type: 'line',
				  data: {
				    labels: xAxisLabelsArray,
				    datasets: [{
				      label: labelText,
				      data: dataArray,
				      backgroundColor: [
				        'rgba(255, 99, 132, 0.6)',
				        'rgba(54, 162, 235, 0.6)',
				        'rgba(255, 206, 86, 0.6)',
				        'rgba(75, 192, 192, 0.6)',
				        'rgba(153, 102, 255, 0.6)',
				        'rgba(255, 159, 64, 0.6)',
				        'rgba(255, 99, 132, 0.6)',
				        'rgba(54, 162, 235, 0.6)',
				        'rgba(255, 206, 86, 0.6)',
				        'rgba(153, 102, 255, 0.6)'
				      ]
				    }]
				  },
				  options: {
		                responsive: false
		            }
				}); //END var barChart

		});
		
	});
});