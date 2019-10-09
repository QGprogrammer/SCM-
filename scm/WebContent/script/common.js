String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}


function openWindow(URL, Name, Width, Height) {
	window.open(URL, Name, "width=" + Width + ",height=" + Height +
				",left=" + (window.screen.width - Width)/2 +
				",top=" + (window.screen.height - Height)/2 +
				",resizable=no,scrollbars=yes,toolbar=no,location=no," +
				"directories=no,status=no,menubar=no");	
}