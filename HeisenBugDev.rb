require 'base64'
require 'json'
require 'net/http'

manifest = JSON.parse(IO.read('build/libs/manifest.json'))

req = Net::HTTP::Post.new('/builds.json', 'Content-Type' => 'application/json')
req['X-User-Email'] = ENV['HBD_EMAIL_PROD']
req['X-User-Token'] = ENV['HBD_TOKEN_PROD']
req.body = manifest.to_json

response = Net::HTTP.new('heisenbugdev.com').start do |http|
    http.request(req)
end

json = JSON.parse(response.body)

p json
upload_path = json['upload_path']
puts upload_path
puts response.body
puts response

req.body = JSON.parse(
'{
    "file_type": "universal",
    "file": "build/libs/BoatCraft-1.7.2-v2.0.1-100-universal.jar"
}')

puts 'heisenbugdev.com' + upload_path

if false then
response = Net::HTTP.new('heisenbugdev.com' + upload_path).start do |http|
    http.request(req)
end
end
