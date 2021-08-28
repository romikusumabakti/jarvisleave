import {html} from '../js/standalone.module.js';

function TableCell(props) {

    return html`
        <th class="mdc-data-table__cell${props.numeric ? ' mdc-data-table__cell--numeric' : ''}">
            ${props.children}
        </th>
    `;
}

export default TableCell;