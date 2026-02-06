from flask import Flask, jsonify
import os
import time
import random
import json

app = Flask(__name__)

@app.route('/enrich/<user_id>')
def enrich(user_id):
    delay = int(os.getenv('MOCK_SERVICE_DELAY_MS', '200')) / 1000.0
    time.sleep(delay)
    
    failure_rate = float(os.getenv('MOCK_SERVICE_FAILURE_RATE', '0.4'))
    if random.random() < failure_rate:
        return '', 500
    
    data = {
        'recentActivity': ['login', 'purchase', f'activity for {user_id}'],
        'loyaltyScore': random.randint(50, 100)
    }
    return jsonify(data)

@app.route('/health')
def health():
    return jsonify({'status': 'healthy'})

if __name__ == '__main__':
    port = int(os.getenv('MOCK_PORT', 8081))
    app.run(host='0.0.0.0', port=port, debug=False)
