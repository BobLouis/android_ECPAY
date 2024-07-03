import React from 'react';
import { StyleSheet, Text, View, Button, NativeModules, Alert } from 'react-native';
import { StatusBar } from 'expo-status-bar';

const { ECPayModule } = NativeModules;

export default function App() {
  const handleInitialize = () => {
    ECPayModule.initializeSDK();
    Alert.alert("ECPay SDK", "ECPay SDK Initialized");
  };

  const handlePayment = () => {
    const token = "your-token-here"; // Replace with your actual token
    const language = "zh-TW"; // Language code
    const useResultPage = 1; // 1 to use result page, 0 otherwise
    const appStoreName = "MyAppStore"; // App store name

    ECPayModule.createPayment(token, language, useResultPage, appStoreName, (result) => {
      Alert.alert("Payment Result", JSON.stringify(result));
    });
  };

  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>
      <Button title="Initialize ECPay" onPress={handleInitialize} />
      <Button title="Pay Now" onPress={handlePayment} />
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
