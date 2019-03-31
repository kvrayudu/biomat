    $(document).ready(function () { 
    	
    	$('#selectedFormulaId').inputpicker({
    	    url: 'getFormula',
    	    fields:['id','name', 'formulaDesc'],
    	    fieldText : 'name',
    	    fieldValue : 'id',
    	    headShow: true,
    	    filterOpen: true,
    	    autoOpen: true
    	});
    	
    	
    	$('#selectedMaterialId').inputpicker({
    	    url: 'getMaterials',
    	    fields:['id','shortDesc'],
    	    fieldText : 'shortDesc',
    	    fieldValue : 'id',
    	    headShow: true,
    	    filterOpen: true,
    	    autoOpen: true,
    	    width: '100%',
    	    headShow: true,
    	});
    	
    	
        

    	
    	$("#selectedFormulaId" ).on( "change", function() {
    		$("#formulaMaterialAssociationForm").submit();
        });

    	
    	$("#addMaterialToFormula" ).on( "click", function() {
    		 var materialId = $("#selectedMaterialId").val();
    		 if(materialId == null || materialId == ''){
				$.alert({
					title: 'Error!',
					content: 'Please select a Material before adding to the Formula',
				});
				return;
    		 }
    		  
    		 $("#userAction").val("addMaterialToFormula");
    		 $("#selectedBioMaterialId").val(materialId);
    		$("#formulaMaterialAssociationForm").submit();
        });
    	
    	$("button").click(function() {
    		if(this.id=='deleteMaterialFromFormula'){
    			 var materialId = this.value;
		   		 $("#userAction").val("deleteMaterialFromFormula");
				 $("#selectedBioMaterialId").val(materialId);
				 $("#formulaMaterialAssociationForm").submit();
    		}
    		
    	});
    	
    	//$("#deleteMaterialFromFormula" ).on( "click", function() {
    	//	alert("");
        //});
    	
    	
        
    });//function
