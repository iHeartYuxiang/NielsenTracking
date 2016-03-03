/** dynamically load Script */
(function(open, callback) {

	var script = document.createElement('script');
	var head = document.getElementsByTagName('head')[0];
	var done = false;
	
	  script.onload = script.onreadystatechange = (function() {
            if (!done && (!this.readyState || this.readyState == 'loaded' 
                    || this.readyState == 'complete')) {
                done = true;
                script.onload = script.onreadystatechange = null;
                head.removeChild(script);
                callback();
            }//if
        });
	
	script.src = 'hijackAJAX.js';
	head.appendChild(script);
	
	
})(XMLHttpRequest.prototype.open, arguments[arguments.length - 1]);	        


