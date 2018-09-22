$(document).ready(function () {
    var next = 0;
    $("#add-more").click(function(e){
        e.preventDefault();
        var addto = "#field" + next;
        
        
        next = next + 1;
        //var newIn = ' <div id="field'+ next +'" name="field'+ next +'"><!-- Text input--><div class="form-group"> <label class="col-md-4 control-label" for="action_id">Action Id</label> <div class="col-md-5"> <input id="action_id" name="action_id" type="text" placeholder="" class="form-control input-md"> </div></div><br><br> <!-- Text input--><div class="form-group"> <label class="col-md-4 control-label" for="action_name">Action Name</label> <div class="col-md-5"> <input id="action_name" name="action_name" type="text" placeholder="" class="form-control input-md"> </div></div><br><br><!-- File Button --> <div class="form-group"> <label class="col-md-4 control-label" for="action_json">Action JSON File</label> <div class="col-md-4"> <input id="action_json" name="action_json" class="input-file" type="file"> </div></div></div>';
	
        var newIn  = "	<div class=\"form-group row\">\r\n" + 
		"					\r\n" + 
		"						<div class=\"col-sm-2\">\r\n" + 
		"							&nbsp;\r\n" + 
		"						</div>\r\n" + 
		"						<div class=\"col-sm-2\">\r\n" + 
		"							<label for=\"measurementPairs"+next+".measurementValue\" class=\"text-info\">Measurement Value</label>\r\n" + 
		"						</div>\r\n" + 
		"						\r\n" + 
		"						<div class=\"col-sm-2\">\r\n" + 
		"							<label for=\"measurementPairs"+next+".errorValue\" class=\"text-info\">Error Value</label>\r\n" + 
		"						</div>\r\n" + 
		"						<div class=\"col-sm-6\">\r\n" + 
		"							&nbsp;\r\n" + 
		"						</div>\r\n" + 
		"						\r\n" + 
		"					</div>\r\n" + 
		"					\r\n" + 
		"					<div class=\"form-group row\">\r\n" + 
		"						<div class=\"col-sm-2\">\r\n" + 
		"							&nbsp;\r\n" + 
		"						</div>\r\n" + 
		"						<div class=\"col-sm-2\">\r\n" + 
		"							<input id=\"measurementPairs["+next+"].measurementValue\" name=\"measurementPairs["+next+"].measurementValue\" placeholder=\"Enter Measurement Value\" class=\"form-control\" type=\"text\" />\r\n" + 
		"						</div>\r\n" + 
		"						<div class=\"col-sm-2\">\r\n" + 
		"							<input id=\"measurementPairs["+next+"].errorValue\" name=\"measurementPairs["+next+"].errorValue\" placeholder=\"Enter Error Value\" class=\"form-control\" type=\"text\" />\r\n" + 
		"						</div>\r\n" + 
		"						<div class=\"col-sm-6\">\r\n" + 
		"							&nbsp;\r\n" + 
		"						</div>\r\n" + 
		"					</div>\r\n" + 
		"";

        newIn+="  <div id=\"field"+next+"\"></div>"; 
		 
		 

        
        var newInput = $(newIn);
        $(addto).after(newInput);
        
        $("#field" + next).attr('data-source',$(addto).attr('data-source'));
        $("#count").val(next);  

    });

});