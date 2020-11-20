<?php
require 'phpmailer/class.phpmailer.php';
require 'phpmailer/class.smtp.php';
require 'phpmailer/PHPMailerAutoload.php';

function sendMail($email, $subject, $message){
   $mail = new PHPMailer;
   $mail->isSMTP();
   $mail->Host = 'smtp.zoho.com';
   $mail->Port = 465;
   $mail->SMTPAuth = true;
   $mail->SMTPSecure = 'ssl';
   $mail->Pool = true;
   $mail->Mailer   = 'smtp';
   $mail->Username = 'info@bluebase.in';
   $mail->Password = 'Welcome@321';

   $mail->setFrom('info@bluebase.in', 'ATS');
   $mail->addReplyTo('info@bluebase.in', 'ATS');
   $mail->addAddress($email);

   $mail->isHTML(true);	
   $mail->Subject = $subject;
   $mail->Body = $message;

   if($mail->send()){
       return true;
   }else{
       return false;
   }
}

?>