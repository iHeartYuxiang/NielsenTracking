/** dynamically load Script */
(function(open, callback) {
  XMLHttpRequest.prototype.open = function(method, url, async, user, pass) {
    // Do some magic
    console.log('see ajax calls:' + url);
    open.call(this, method, rewrittenUrl, async, user, pass);
  };
  callback();
})(XMLHttpRequest.prototype.open,arguments[arguments.length - 1]);