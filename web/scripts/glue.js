function loadScript(url, callback)
{
    var h = document.getElementsByTagName('head')[0];
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = url;

    script.onreadystatechange = callback;
    script.onload = callback;

    h.appendChild(script);
}

loadScript('../libraries/head/head.load.min.js', function () {
    loadScript('./scripts/libraries.js', function () {
        head.load(
            getFiles()
        )
    });
});
