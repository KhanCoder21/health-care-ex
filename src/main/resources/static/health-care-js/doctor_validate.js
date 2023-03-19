$(document).ready(function () {
  // 1. Hide error section
  $("#firstNameError").hide();
  $("#lastNameError").hide();
  $("#emailError").hide();
  $("#specializationError").hide();
  $("#mobileError").hide();
  $("#doctorNoteError").hide();

  // 2. define variable
  var firstNameError = false;
  var lastNameError = false;
  var emailError = false;
  var specializationError = false;
  var mobileError = false;
  var doctorNoteError = false;

  // 3. define validate function
  function validate_firstName() {
    var value = $("#firstName").val();
    console.log("valueee->" + value);
    var exp = /^[A-Z]{3,20}$/;
    if (value == "") {
      $("#firstNameError").show();
      $("#firstNameError").html("*First Name can not be empty");
      $("#firstNameError").css("color", "red");
      firstNameError = false;
    } else if (!exp.test(value)) {
      $("#firstNameError").show();
      $("#firstNameError").html(
        "*First Name must be 3 to 20 characters without digits & spaces"
      );
      $("#firstNameError").css("color", "red");
      firstNameError = false;
    } else {
      $("#firstNameError").hide();
      firstNameError = true;
    }
    return firstNameError;
  }
  function validate_lastName() {
    var value = $("#lastName").val();
    var exp = /^[A-Z]{3,20}$/;
    if (value == "") {
      $("#lastNameError").show();
      $("#lastNameError").html("*Last Name can not be empty");
      $("#lastNameError").css("color", "red");
      lastNameError = false;
    } else if (!exp.test(value)) {
      $("#lastNameError").show();
      $("#lastNameError").html(
        "*LastName must be 3 to 25 characters without digits & spaces"
      );
      $("#lastNameError").css("color", "red");
      lastNameError = false;
    } else {
      $("#lastNameError").hide();
      lastNameError = true;
    }
    return lastNameError;
  }

  function validate_email() {
    var value = $("#email").val();
    var exp =
      /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (value == "") {
      $("#emailError").show();
      $("#emailError").html("*Email Id can not be empty");
      $("#emailError").css("color", "red");
      emailError = false;
    } else if (!exp.test(value)) {
      $("#emailError").show();
      $("#emailError").html("*Email Id must be 6 to 50 characters");
      $("#emailError").css("color", "red");
      emailError = false;
    } else {
      var id = 0; // for register page
      if ($("#id").val() != undefined) {
        // for edit page
        emailError = true;
        id = $("#id").val();
      }
      $.ajax({
        url: "checkEmail",
        data: {
          email: value,
          id: id,
        },
        success: function (respTxt) {
          if (respTxt != "") {
            $("#emailError").show();
            $("#emailError").html(respTxt);
            $("#emailError").css("color", "red");
            emailError = false;
          } else {
            $("#emailError").hide();
            emailError = true;
          }
        },
      });
    }
    return emailError;
  }

  function validate_specialization() {
    var value = $("#specialization").val();
    if (value == "") {
      $("#specializationError").show();
      $("#specializationError").html("*Specialization can not be empty");
      $("#specializationError").css("color", "red");
      specializationError = false;
    } else {
      $("#specNameError").hide();
      specializationError = true;
    }
    return specializationError;
  }

  function validate_mobile() {
    var value = $("#mobile").val();
    if (value == "") {
      $("#mobileError").show();
      $("#mobileError").html("*Mobile Number can not be empty");
      $("#mobileError").css("color", "red");
      mobileError = false;
    } else {
      $("#mobileError").hide();
      mobileError = true;
    }
    return mobileError;
  }

  function validate_doctorNote() {
    var value = $("#note").val();
    if (value == "") {
      $("#doctorNoteError").show();
      $("#doctorNoteError").html("*Note can not be empty");
      $("#doctorNoteError").css("color", "red");
      doctorNoteError = false;
    } else {
      $("#doctorNoteError").hide();
      doctorNoteError = true;
    }
    return doctorNoteError;
  }

  // 4. action event
  $("#firstName").keyup(function () {
    $(this).val($(this).val().toUpperCase());
    validate_firstName();
  });

  $("#lastName").keyup(function () {
    $(this).val($(this).val().toUpperCase());
    validate_lastName();
  });

  $("#email").keyup(function () {
    validate_email();
  });

  $("#specialization").keyup(function () {
    validate_specialization();
  });

  $("#mobile").keyup(function () {
    validate_mobile();
  });

  $("#note").keyup(function () {
    validate_doctorNote();
  });

  // 5. on submit
  $("#docForm").submit(function () {
    validate_firstName();
    validate_lastName();
    validate_email();
    validate_specialization();
    validate_mobile();
    validate_doctorNote();
    if (
      firstNameError &&
      lastNameError &&
      emailError &&
      specializationError &&
      doctorNoteError &&
      mobileError
    ) {
      return true;
    } else return false;
  });
});
