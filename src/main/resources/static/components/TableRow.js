import {html} from '../js/standalone.module.js';

function TableRow(props) {

    return html`
        <tr class="mdc-data-table__row">
            ${props.children}
        </tr>
    `;
}

export default TableRow;