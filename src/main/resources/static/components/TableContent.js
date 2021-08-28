import {html} from '../js/standalone.module.js';

function TableContent(props) {

    return html`
        <tbody class="mdc-data-table__content">
            ${props.children}
        </tbody>
    `;
}

export default TableContent;