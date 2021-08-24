let overlayContainer, overlayBackdrop;

function openDialog() {
    overlayContainer = document.createElement('div');
    overlayContainer.className = 'overlay-container';
    document.body.appendChild(overlayContainer);

    overlayBackdrop = document.createElement('div');
    overlayBackdrop.className = 'overlay-backdrop';
    overlayBackdrop.onclick = () => closeDialog();
    overlayContainer.appendChild(overlayBackdrop).focus();

    const wrapper = document.createElement('div');
    wrapper.className = 'overlay-wrapper';
    overlayContainer.appendChild(wrapper);

    overlayBackdrop.classList.add('overlay-backdrop-showing');
}

function closeDialog() {
    overlayBackdrop.classList.remove('overlay-backdrop-showing')
    // overlayContainer.innerHTML = '';
}