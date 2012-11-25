/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// Checks if inputBox value is equal to value, then selects all the text to make it
// easier for a user to enter their username
function checkFocus(inputBox, value){
    if (inputBox.value.trim() == value) {
        if (event.which != 13 && event.which != 1){ 
            inputBox.style.textAlign = "left";
        } else{
            inputBox.style.textAlign = "center";
        }
        inputBox.focus();
        inputBox.select();
    }
}

// Checks if inputBox value is empty, if it is, it sets it equal to value and 
// makes the inputBox text color gray. If not, it makes the text color blue

function usernameInputCheck(inputBox, value){
    if (inputBox.value.trim() == value || inputBox.value.trim() == "" ){
        inputBox.style.textAlign = "center";
        inputBox.style.color = "gray";        
        inputBox.value = value;

    } else {
        inputBox.style.textAlign = "left";
        inputBox.style.color="blue";
    }

}



// Show description of menu button
function showDescription(section){
    var _desc = new Array();
    _desc['search'] = "search the inventory of available items";
    _desc['borrow'] = "looking for something particular?<br>head over to this section to browse around the categories.";
    _desc['lend'] = "got something to lend? head here to put it up for others to find.";
    _desc[''] = "";
    
    document.getElementById('description').innerHTML = _desc[section];    
}


// Sets the class of the current page to 'active' for css to show correctly
function activateMenu(){    
    var sPath = window.location.pathname;
    var sPage = sPath.substring(sPath.lastIndexOf('/') + 1, sPath.lastIndexOf('.'));
    //alert (sPage);
    
    // workaround to set the home navigation id correctly
    if (sPage == '/Share/'){
        sPage = 'home';
    }
    document.getElementById('menu_'+sPage).className = 'active';    
}