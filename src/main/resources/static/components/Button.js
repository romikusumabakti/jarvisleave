import {html} from '../js/standalone.module.js';
import {useEffect} from "../js/hooks.module.js";

function Button(props) {

    useEffect(() => {
        mdc.ripple.MDCRipple.attachTo(document.querySelector('.foo-button'));
    }, []);

    return html`
        <button class="mdc-button${
                props.variant === 'contained' ? ' mdc-button--raised'
                        : props.variant === 'outlined' ? ' mdc-button--outlined' : ''
        } foo-button">
            <div class="mdc-button__ripple"></div>
            <span class="mdc-button__label">${props.children}</span>
        </button>
    `;
}

export default Button;