from collections import defaultdict

import requests


class Inventory:
    @staticmethod
    def games():
        result = defaultdict(list)
        raise Exception('Remove this line and put your id below!')
        response = requests.get('http://steamcommunity.com/id/<put your id here>/inventory/json/753/6')
        json = response.json()
        descriptions = json.get('rgDescriptions')
        for key in descriptions.keys():
            description = descriptions.get(key)
            market_fee_app = description.get('market_fee_app')
            market_name = description.get('market_name').replace('(Trading Card)', '').strip()
            item_type = description.get('type')
            if item_type.find('Trading Card') > 0:
                result[market_fee_app].append(market_name)
        return result