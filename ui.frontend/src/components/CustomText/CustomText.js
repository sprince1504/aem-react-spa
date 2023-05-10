import React, {Component} from 'react';
import {MapTo} from '@adobe/aem-react-editable-components';

require('./CustomText.css');

export const ImageEditConfig = {

    emptyLabel: 'CustomText',

    isEmpty: function(props) {
        return !props || !props.text || props.text.trim().length < 1;
    }
};

export default class CustomText extends Component {

    render() {
            if(ImageEditConfig.isEmpty(this.props)) {
                return null;
            }

            return (
                    <div className="customText">
                        <h1>{this.props.text}</h1>
                    </div>
            );
        }
}
MapTo('aem-spa-react/components/customText')(CustomText,ImageEditConfig)