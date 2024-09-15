function configureVideoTags() {
  [...document.getElementsByTagName("video")].forEach((v) => {
    v.setAttribute("preload", "auto");
    v.setAttribute("playsinline", true);
    v.setAttribute("controls", true);
    v.setAttribute("controlslist", "nofullscreen nodownload noremoteplayback");

    if (v.classList.contains("article__video-autoplay--looped")) {
      v.setAttribute("loop", true);
      v.play();
    }
  });
}

function cleanAnchorImageTags() {
  [...document.querySelectorAll("a img")].forEach((img) => {
    attachImageToAnchorParent(img, img.parentNode);
  });
}

/**
 *
 * @param {HTMLImageElement} img
 * @param {ParentNode | null} parentNode
 */
function attachImageToAnchorParent(img, parentNode) {
  if (parentNode == null || parentNode.tagName === "DOCUMENT") {
    return;
  } else if (parentNode.tagName === "A") {
    let anchor = parentNode;
    anchor.parentNode?.appendChild(img);
    anchor.parentNode?.removeChild(anchor);
  } else {
    attachImageToAnchorParent(img, parentNode.parentNode);
  }
}

function addImageClickListeners() {
  [...document.getElementsByTagName("img")].forEach((img) => {
    img.addEventListener("click", () => {
      Android.openImage(img.src);
    });
  });
}

function displayContent() {
  setTimeout(() => {
    document.getElementsByTagName("body")[0].classList.add("loaded");
  }, 100);
}

window.onload = () => {
  cleanAnchorImageTags();
  addImageClickListeners();
  configureVideoTags();
};
