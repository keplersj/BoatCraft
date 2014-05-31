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

p json['upload_path']
puts response.body
puts response

if false do
response = Net::HTTP.new('heisnebugdev.com' + upload_path).start do |http|
    
end
end
