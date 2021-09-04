import html from "../modules/htm.js"
import {Icon} from "../modules/material-ui.js"

function MaterialIcon(props) {
    return html`
        <${Icon} className="material-icons-outlined" fontSize=${props.size}>
            ${props.children}
        <//>
    `;
}

export default MaterialIcon;