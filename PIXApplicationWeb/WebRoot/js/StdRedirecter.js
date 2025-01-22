function StdRedirecter(ajCall, resParam) {
  if (resParam == null || resParam == "") {
    window.location.href = ajCall.xhr.responseText;
  } else {
    window.location.href = resParam;
  }
}
