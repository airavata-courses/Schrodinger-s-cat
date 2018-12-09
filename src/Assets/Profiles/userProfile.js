var UserProfile = (function() {
    var userId = "";
    var userName = "";
    
    var getUserId = function() {
        return userId;
    }

    var getUserName = function() {
      return userName;    // Or pull this from cookie/localStorage
    };
  
    var setUserName = function(name) {
        userName = name;     
      // Also set this in cookie/localStorage
    };
    var setUserId = function(myId) {
        userId = myId;     
      // Also set this in cookie/localStorage
    };
  
    return {
        getUserName: getUserName,
        setUserName: setUserName,
        getUserId: getUserId,
        setUserId: setUserId
    }
  
  })();
  
  export default UserProfile;