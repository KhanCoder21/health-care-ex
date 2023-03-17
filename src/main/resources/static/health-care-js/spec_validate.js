$(document).ready(function () {
  // 1. Hide error section
  $("#specCodeError").hide();
  $("#specNameError").hide();
  $("#specNoteError").hide();

  // 2. define variable
  var specCodeError = false;
  var specNameError = false;
  var specNoteError = false;

  // 3. define validate function
  function validate_specCode() {
    var value = $("#specCode").val();
    var exp = /^[A-Z]{4,12}$/;
    if (value == "") {
      $("#specCodeError").show();
      $("#specCodeError").html("*Code can not be empty");
      $("#specCodeError").css("color", "red");
      specCodeError = false;
    } else if (!exp.test(value)) {
      $("#specCodeError").show();
      $("#specCodeError").html(
        "*Code must be 4 to 12 characters without digits & spaces"
      );
      $("#specCodeError").css("color", "red");
      specCodeError = false;
    } else {
    	var id = 0; // for register page
    	if($("#id").val() != undefined){ // for edit page
    		 specCodeError = true;
    		 id = $("#id").val();
    	}
      $.ajax({
        url: "checkcode",
        data: {
          "code": value, "id": id
        },
        success: function (respTxt) {
          if (respTxt != "") {
            $("#specCodeError").show();
            $("#specCodeError").html(respTxt);
            $("#specCodeError").css("color", "red");
            specCodeError = false;
          } else {
            $("#specCodeError").hide();
            specCodeError = true;
          }
        },
      });
    }
    return specCodeError;
  }

  function validate_specName() {
    var value = $("#specName").val();
    var exp = /^[A-Za-z0-9\s\.]{4,30}$/;
    if (value == "") {
      $("#specNameError").show();
      $("#specNameError").html("*Name can not be empty");
      $("#specNameError").css("color", "red");
      specNameError = false;
    } else if (!exp.test(value)) {
      $("#specNameError").show();
      $("#specNameError").html("*name must be 4 to 25 characters");
      $("#specNameError").css("color", "red");
      specNameError = false;
    } else {
      $("#specNameError").hide();
      specNameError = true;
    }
    return specNameError;
  }

  function validate_specNote() {
    var value = $("#specNote").val();
    if (value == "") {
      $("#specNoteError").show();
      $("#specNoteError").html("*Note can not be empty");
      $("#specNoteError").css("color", "red");
      specNoteError = false;
    } else {
      $("#specNoteError").hide();
      specNoteError = true;
    }
    return specNoteError;
  }

  // 4. action event
  $("#specCode").keyup(function () {
    $(this).val($(this).val().toUpperCase());
    validate_specCode();
  });
  $("#specName").keyup(function () {
    $(this).val($(this).val().toUpperCase());
    validate_specName();
  });
  $("#specNote").keyup(function () {
    validate_specNote();
  });
  // 5. on submit
  $("#specForm").submit(function () {
    validate_specCode();
    validate_specName();
    validate_specNote();
    if (specCodeError && specNameError && specNoteError) return true;
    else return false;
  });
});
