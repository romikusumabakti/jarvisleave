import html from "../js/htm.js"
import {Icon} from "../js/material-ui.js"

function MaterialIcon(props) {
    return html`
        <${Icon} baseClassName="material-icons-outlined">${props.children}<//>
    `;
}

export default MaterialIcon;